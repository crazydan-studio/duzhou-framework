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

package io.crazydan.duzhou.framework.ui.vendor.jexer.component;

import io.crazydan.duzhou.framework.ui.layout.XuiLayoutNode;
import io.crazydan.duzhou.framework.ui.vendor.core.XuiComponentTreeNode;
import io.crazydan.duzhou.framework.ui.vendor.jexer.JexerComponent;
import jexer.TAction;
import jexer.TButton;
import jexer.TWidget;

/**
 *
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-11-25
 */
public class JexerButton extends JexerComponent {
    public static final String NAME = "button";

    public JexerButton(JexerComponent parent, XuiComponentTreeNode node) {
        super(parent, node, XuiLayoutNode.item(null, null));
    }

    @Override
    protected TWidget createWidget(TWidget parent, XuiComponentTreeNode node) {
        String label = (String) node.nativeProps.get("label");
        TButton widget = new TButton(parent, label, 0, 0, new TAction() {
            @Override
            public void DO() {
                // TODO TButton click event
            }
        });

        widget.setActive(false);

        return widget;
    }
}
