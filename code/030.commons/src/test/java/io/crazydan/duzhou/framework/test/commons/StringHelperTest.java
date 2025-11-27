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

package io.crazydan.duzhou.framework.test.commons;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static io.crazydan.duzhou.framework.commons.StringHelper.snakeCase;
import static io.crazydan.duzhou.framework.commons.StringHelper.trimAllLinesByFirstNonBlankLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:flytreeleft@crazydan.org">flytreeleft</a>
 * @date 2024-05-03
 */
public class StringHelperTest {

    @Test
    public void test_snakeCase() {
        assertEquals("abcd", snakeCase("abCd", false, false));
        assertEquals("abcd", snakeCase("abCD", false, false));
        assertEquals("ab_cd", snakeCase("ab-Cd", false, false));
        assertEquals("ab_cd", snakeCase("ab_Cd", false, false));
        assertEquals("ab_cd", snakeCase("ab cd", false, false));
        assertEquals("ab_cd", snakeCase("ab%-cd", false, false));
        assertEquals("ab_cd", snakeCase("ab_cd", false, false));
        assertEquals("ab__cd", snakeCase("ab__cd", false, false));
        assertEquals("ab__cd", snakeCase("ab _cd", false, false));
    }

    @Test
    public void test_trimAllLinesByFirstNonBlankLine() {
        Map<String, String> samples = new LinkedHashMap<>() {{
            put("  \n\n", "");
            put("  \n\n  ", "");
            put("  \n  \n", "");
            put("  \n  \n  ", "");
            put("    A\n    B\n", "A\nB");
            put("    A\n    B\n  ", "A\nB");
            put("\n    A\n    B\n", "A\nB");
            put("\n    A  \n    \n  B\n      C  \n  \n", "A\n\nB\n  C");
            put("\n    A  \r\n    \n  B\r\n      C  \n  \n", "A\n\nB\n  C");
        }};

        samples.forEach((sample, expected) -> {
            assertEquals(expected, trimAllLinesByFirstNonBlankLine(sample));
        });
    }
}
