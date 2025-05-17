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

package io.crazydan.duzhou.framework.ui.runtime.web;

import io.crazydan.duzhou.framework.commons.UnitNumber;
import io.crazydan.duzhou.framework.ui.domain.type.XuiSize;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2025-05-17
 */
public class XuiGenConfig {
    private String exprPrefix;
    private String exprSuffix;

    private UnitNumber fontSize;
    private UnitNumber baseSize;
    private UnitNumber lineSize;

    public String getExprPrefix() {
        return this.exprPrefix;
    }

    public void setExprPrefix(String exprPrefix) {
        this.exprPrefix = exprPrefix;
    }

    public String getExprSuffix() {
        return this.exprSuffix;
    }

    public void setExprSuffix(String exprSuffix) {
        this.exprSuffix = exprSuffix;
    }

    public UnitNumber getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(UnitNumber fontSize) {
        this.fontSize = fontSize;
    }

    /** 将 {@link XuiSize} 转换为运行时尺寸 */
    public UnitNumber fromXuiSize(XuiSize size) {
        if (size.unit == XuiSize.Unit.percent) {
            return UnitNumber.create(size.value, "%");
        }

        UnitNumber base = getBaseSize();
        switch (size.unit) {
            case a_line: {
                base = getLineSize();
                break;
            }
        }

        float value = size.value * base.number.floatValue();

        return UnitNumber.create(value, base.unit);
    }

    public UnitNumber getBaseSize() {
        return this.baseSize;
    }

    public void setBaseSize(UnitNumber baseSize) {
        this.baseSize = baseSize;
    }

    public UnitNumber getLineSize() {
        return this.lineSize;
    }

    public void setLineSize(UnitNumber lineSize) {
        this.lineSize = lineSize;
    }
}
