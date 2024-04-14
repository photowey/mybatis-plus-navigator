/*
 * Copyright © 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.photowey.mybatisplus.navigator.core.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.photowey.mybatisplus.navigator.annotation.symbol.NotNull;
import io.github.photowey.mybatisplus.navigator.core.enums.NamingStrategy;
import io.github.photowey.mybatisplus.navigator.core.exception.NavigatorRuntimeException;
import io.github.photowey.mybatisplus.navigator.core.thrower.AssertionErrorThrower;

import java.lang.reflect.Field;

/**
 * {@code CriteriaUtils}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/02
 */
public final class CriteriaUtils {

    private CriteriaUtils() {
        AssertionErrorThrower.throwz(CriteriaUtils.class);
    }

    public static String PascalCase(final @NotNull String target) {
        assert target != null;
        final char[] buf = target.toCharArray();
        if (buf[0] >= 'a' && buf[0] <= 'z') {
            final int n = 0;
            buf[n] -= ' ';
        }

        return String.valueOf(buf);
    }

    public static String camelCase(final @NotNull String target) {
        assert target != null;
        final char[] buf = target.toCharArray();
        if (buf[0] >= 'A' && buf[0] <= 'Z') {
            final int n = 0;
            buf[n] += ' ';
        }

        return String.valueOf(buf);
    }

    public static Object tryExtractFiledValue(final Field field, final Object query) {
        field.setAccessible(true);
        try {
            return field.get(query);
        } catch (IllegalAccessException e) {
            throw new NavigatorRuntimeException(e);
        }
    }

    public static String tryTranslateToColumnName(final Field field, final NamingStrategy strategy) {
        String fieldName = field.getName();
        switch (strategy) {
            case CAMEL_CASE:
                return CriteriaUtils.camelCase(fieldName);
            case SNAKE_CASE:
                return StringUtils.camelToUnderline(fieldName);
            case PASCAL_CASE:
                return CriteriaUtils.PascalCase(fieldName);
            case UPPER_SNAKE_CASE:
                return StringUtils.camelToUnderline(fieldName).toUpperCase();
            default:
                throw new NavigatorRuntimeException("unknown column naming strategy:%s", strategy.name());
        }
    }
}
