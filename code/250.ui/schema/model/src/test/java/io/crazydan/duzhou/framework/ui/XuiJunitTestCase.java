/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的应用生产平台
 * Copyright (C) 2025 Crazydan Studio <https://studio.crazydan.org>
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

package io.crazydan.duzhou.framework.ui;

import io.crazydan.duzhou.framework.junit.NopJunitTestCase;
import io.crazydan.duzhou.framework.ui.schema.component.XuiComponent;
import io.crazydan.duzhou.framework.ui.schema.component.template.XuiComponentTemplate;
import io.nop.core.lang.xml.XNode;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-22
 */
public abstract class XuiJunitTestCase extends NopJunitTestCase {

    protected XNode toXNode(XuiComponent component) {
        return toXNode(XuiConstants.XDSL_SCHEMA_COMPONENT, component);
    }

    protected XNode toXNode(XuiComponentTemplate template) {
        return toXNode(XuiConstants.XDSL_SCHEMA_COMPONENT_TEMPLATE, template);
    }

    protected String toXml(XNode node) {
        return node.clearComment().clearAttrs().xml();
    }

    protected String cleanXml(String xml) {
        return xml.replaceAll("\n\\s*", "");
    }
}
