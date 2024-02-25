/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 */

import { parse as qsparse } from 'qs';

const GRAPHQL_PATH = '/graphql';
const GRAPHQL_OP_TYPES = ['query', 'mutation', 'subscription'];

export function requestAdaptor(api) {
  // Note：在 api.transformResponse 可设置转换器（可以为数组类型，以设置多个转换器）
  if (handleGraphQL(api, GRAPHQL_PATH, {})) {
    return api;
  }

  return api;
}

export function responseAdaptor(api, payload, query, request, response) {
  return payload;
}

// ====================================================
//  以下代码改造自 Nop Chaos:
//  - https://github.com/entropy-cloud/nop-chaos/blob/master/packages/nop-core
//  - https://github.com/entropy-cloud/nop-chaos/blob/master/packages/nop-core/src/core/graphql.ts
//  - https://github.com/entropy-cloud/nop-chaos/blob/master/packages/sdk/lib/sdk.js
// ====================================================
/**
 * @url {string} 类似 `@query:PageProvider__getPage?path=/path/to/page.page.xml`
 */
function handleGraphQL(config, graphqlUrl, options) {
  const url = config.url;
  const [type, path] = splitPrefixUrl(url) || [];

  if (GRAPHQL_OP_TYPES.includes(type)) {
    normalizeData(config, path);
    handleGraphQLUrl(type, path, config, graphqlUrl, options);

    config.method = 'post';

    return true;
  } else {
    return false;
  }
}
function splitPrefixUrl(url) {
  if (url.startsWith('@')) {
    let pos2 = url.indexOf(':');
    if (pos2 < 0) return;

    return [url.substring(1, pos2), url.substring(pos2 + 1).trim()];
  }

  let pos = url.indexOf('://');
  if (pos < 0) return;

  return [url.substring(0, pos), url.substring(pos + 3)];
}
function transformGraphQLResponse(data) {
  data = JSON.parse(data);

  const extensions = data.extensions || {};

  if (data.errors && data.errors.length > 0) {
    data.status = parseInt(extensions['nop-status'] || -1);
    data.msg = data.errors[0].message;
  } else {
    data.status = 0;
    data.msg = extensions['nop-msg'];
  }
  return data;
}
function handleGraphQLUrl(opType, url, config, graphql, options) {
  let pos = url.indexOf('?');
  if (pos > 0) {
    url = url.substring(0, pos);
  }

  let pos2 = url.indexOf('/');
  const action = pos2 > 0 ? url.substring(0, pos2) : url;
  let selection = pos2 > 0 ? url.substring(pos2 + 1) : void 0;
  if (selection) {
    selection = selection.replaceAll('%20', ' ');
    selection = selection.replaceAll('%0A', '\n');
  }
  if (!selection) {
    selection = options['gql:selection'];
  }

  let stdAction = action;
  let pos3 = action.lastIndexOf('_');
  if (pos3 > 0) {
    stdAction = action.substring(pos3 + 1);
  }

  let data = config.data || {};
  if (stdAction === 'findPage') {
    if (data.op === 'loadOptions') {
      const values = toArray(data.value, options.delimiter);
      data = {
        ['filter_' + options.valueField + '__in']: values
      };
      selection =
        'items{' +
        (options.valueField || 'id') +
        ',' +
        (options.labelField || 'id') +
        '}';
    }
  }

  let def = operationRegistry[stdAction];
  if (!def) {
    def = guessDefinition(config.data);
  }
  let args = [...def.arguments, ...guessExtArgDefinitions(config.data)];
  let query = opType + ' ' + action;
  if (args.length > 0) {
    query += '(';
    query += args.map((arg) => '$' + arg.name + ':' + arg.type).join(',');
    query += ')';
  }
  query += '{\n';
  query += action + '(';
  if (args.length > 0) {
    query += args.map((arg) => arg.name + ':$' + arg.name).join(',');
  }
  query += ')';
  if (selection) {
    query += '{\n';
    query += selection;
    query += '\n}';
  }
  query += '\n}';
  const variables = {};
  args.forEach((arg) => {
    const builder = arg.builder || defaultArgBuilders[arg.type] || argValue;
    variables[arg.name] = builder(data, arg, options);
  });
  config.transformResponse = [
    transformGraphQLResponse,
    (res) => {
      res.data && (res.data = res.data[action]);
      return res;
    }
  ];

  config.url = graphql;
  config.data = {
    query,
    variables
  };
}
function toArray(value, delimiter) {
  if (isString(value)) {
    value = value.split(delimiter || ',');
  }
  return value;
}
function normalizeData(config, path) {
  const pos = path.indexOf('?');
  if (pos > 0) {
    config.params = qsparse(path.substring(pos + 1));
  }

  const { data, params } = splitData(config.params);
  config.data = { ...filterData(config.data), ...data };
  config.params = params;
}
function filterData(data) {
  if (!data) return {};

  const ret = {};
  for (let k in data) {
    if (k.startsWith('__')) continue;

    ret[k] = data[k];
  }
  return ret;
}
function splitData(data) {
  if (!data) return {};

  const body = {};
  const params = {};
  for (let k in data) {
    if (k.startsWith('__')) continue;

    if (k.charAt(0) == '@' || k.charAt(0) == '_') {
      params[k] = data[k];
    } else {
      body[k] = data[k];
    }
  }
  return {
    data: body,
    params
  };
}
function guessDefinition(data) {
  let args = [];
  if (data) {
    for (let k in data) {
      if (isSpecialVarName(k)) continue;

      args.push({ name: k, type: guessType(data[k]) });
    }
  }
  return { arguments: args };
}
function guessExtArgDefinitions(data) {
  let args = [];
  if (data) {
    for (let k in data) {
      if (k.startsWith('v_')) {
        args.push({ name: k, type: guessType(data[k]) });
      }
    }
  }
  return args;
}
function isSpecialVarName(name) {
  return name.startsWith('__') || name.startsWith('@') || name.startsWith('v_');
}
function guessType(val) {
  if (typeof val === 'string') return 'String';
  if (typeof val === 'number') {
    if (Number.isInteger(val)) return 'Int';
    return 'Float';
  }
  if (typeof val === 'boolean') return 'Boolean';
  if (Array.isArray(val)) return '[String]';
  if (val && val.constructor === Object) return 'Map';

  return 'String';
}
const operationRegistry = {
  get: {
    //  operation: 'query',
    arguments: [
      {
        name: 'id',
        type: 'String',
        builder: argString
      },
      {
        name: 'ignoreUnknown',
        type: 'Boolean',
        builder: argBoolean
      }
    ]
  },
  findPage: {
    //  operation: 'query',
    arguments: [
      {
        name: 'query',
        type: 'QueryBeanInput',
        builder: argQuery
      }
    ]
  },
  findList: {
    //  operation: 'query',
    arguments: [
      {
        name: 'query',
        type: 'QueryBeanInput',
        builder: argQuery
      }
    ]
  },
  findFirst: {
    //  operation: 'query',
    arguments: [
      {
        name: 'query',
        type: 'QueryBeanInput',
        builder: argQuery
      }
    ]
  },
  update: {
    //  operation: 'mutation',
    arguments: [
      {
        name: 'data',
        type: 'Map',
        builder: argDataMap
      }
    ]
  },
  save: {
    // operation: 'mutation',
    arguments: [
      {
        name: 'data',
        type: 'Map',
        builder: argDataMap
      }
    ]
  },
  saveOrUpdate: {
    // operation: 'mutation',
    arguments: [
      {
        name: 'data',
        type: 'Map',
        builder: argDataMap
      }
    ]
  },
  upsert: {
    // operation: 'mutation',
    arguments: [
      {
        name: 'data',
        type: 'Map',
        builder: argDataMap
      }
    ]
  },
  copyForNew: {
    // operation: 'mutation',
    arguments: [
      {
        name: 'data',
        type: 'Map',
        builder: argDataMap
      }
    ]
  },
  delete: {
    // operation: 'mutation',
    arguments: [
      {
        name: 'id',
        type: 'String',
        builder: argString
      }
    ]
  },
  batchGet: {
    arguments: [
      {
        name: 'ids',
        type: '[String]',
        builder: argStringList
      }
    ]
  },
  batchDelete: {
    // operation: 'mutation',
    arguments: [
      {
        name: 'ids',
        type: '[String]',
        builder: argStringList
      }
    ]
  },
  batchModify: {
    // operation: 'mutation',
    arguments: [
      {
        name: 'data',
        type: '[Map]',
        builder: argMapList
      },
      {
        name: 'delIds',
        type: '[String]',
        builder: argStringList
      }
    ]
  }
};
const defaultArgBuilders = {
  String: argString,
  Boolean: argBoolean,
  Int: argInt,
  Float: argFloat,
  Map: argMap,
  '[String]': argStringList,
  '[Map]': argMapList,
  QueryBeanInput: argQuery
};
function argString(data, arg) {
  let v = data[arg.name];
  if (v == null) return null;
  return String(v);
}
function argBoolean(data, arg) {
  let v = data[arg.name];
  if (v == null) return null;
  if (v == 'false' || v == 'n' || v == '0' || v == 'N') return false;
  return !!v;
}
function argInt(data, arg) {
  let v = data[arg.name];
  if (v == null) return null;
  return parseInt(v, 10);
}
function argFloat(data, arg) {
  let v = data[arg.name];
  if (v == null) return null;
  return parseFloat(v);
}
function argQuery(data, arg, options) {
  let query = {};
  query.limit = data.limit ?? data.pageSize ?? data.perPage ?? 0;
  query.offset = data.offset ?? query.limit * ((data.page || 0) - 1);
  query.orderBy = toOrderBy(data.orderBy ?? data.orderField, data.orderDir);
  query.filter = toFilter(data);
  query.cursor = data.cursor;
  query.timeout = data.timeout;
  return query;

  function toOrderBy(v, orderDir) {
    if (v == null) return;
    if (isString$1(v)) {
      if (v.length == 0) return;
      if (v.endsWith('_label')) v = v.substring(0, v.length - '_label'.length);
      return [{ name: v, desc: orderDir == 'desc' }];
    }
    if (Array.isArray(v)) return v;
    return [v];
  }
  function toFilter(data2) {
    let filter = {
      $type: 'and',
      $body: []
    };
    for (let k in data2) {
      if (k.startsWith('filter_')) {
        let [name, op] = k.substring('filter_'.length).split('__');
        op = op || 'eq';
        let value2 = data2[k];
        if (value2 == null || value2 == '') continue;
        if (value2 == '__empty') {
          value2 = '';
        } else if (value2 == '__null') {
          value2 = null;
        }
        let min = void 0;
        let max = void 0;
        if (op.startsWith('between') && value2 != null) {
          let ary = toArray(value2);
          min = ary[0];
          max = ary[1];
          value2 = void 0;
        }
        filter.$body.push({ $type: op, name, value: value2, min, max });
      }
    }
    if (options.filter) {
      if (
        options.filter.$type == 'and' ||
        options.filter.$type == '_' ||
        options.filter.$type == 'filter'
      ) {
        filter.$body = filter.$body.concat(options.filter.$body || []);
      } else {
        filter.$body.push(options.filter);
      }
    }
    if (filter.$body.length == 0) return;
    return filter;
  }
}
function argDataMap(data, arg) {
  if (data == null) return null;
  let ret = {};
  for (let k in data) {
    if (isSpecialVarName(k)) continue;
    ret[k] = data[k];
  }
  return ret;
}
function argMap(data, arg) {
  return data[arg.name];
}
function argStringList(data, arg) {
  let v = data[arg.name];
  if (v == null) return null;
  if (typeof v === 'string') return v.split(',');
  return v;
}
function argMapList(data, arg) {
  return data[arg.name];
}
function argValue(data, arg) {
  return data[arg.name];
}
