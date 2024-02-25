/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */

import { Component } from 'react';

export type SchemaProcessorType = {
  componentType: Component;
  editorComponentType: Component;
  transformSchemaIn?(schema: any): any;
  transformSchemaOut?(schema: any): any;
};

const schemaProcessorTypes: Record<string, SchemaProcessorType> = {};

export function registerSchemaProcessorType(
  typeName: string,
  schemaProcessorType: SchemaProcessorType
) {
  schemaProcessorTypes[typeName] = schemaProcessorType;
}

export function getSchemaProcessorType(typeName: string) {
  return schemaProcessorTypes[typeName];
}
