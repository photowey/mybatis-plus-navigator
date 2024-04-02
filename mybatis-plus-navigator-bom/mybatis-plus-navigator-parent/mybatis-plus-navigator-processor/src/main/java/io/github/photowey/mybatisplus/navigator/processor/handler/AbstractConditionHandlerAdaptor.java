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
package io.github.photowey.mybatisplus.navigator.processor.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.photowey.mybatisplus.navigator.core.enums.NamingStrategy;
import io.github.photowey.mybatisplus.navigator.core.util.CriteriaUtils;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;

/**
 * {@code AbstractConditionHandlerAdaptor}
 *
 * @author photowey
 * @date 2024/04/02
 * @since 1.0.0
 */
public abstract class AbstractConditionHandlerAdaptor implements ConditionHandler {

    @Override
    public <T> void handleAnd(QueryWrapper<T> queryWrapper, AbstractQuery<T> query, Field field) {

    }

    @Override
    public <T> void handleOr(QueryWrapper<T> queryWrapper, AbstractQuery<?> query, Field field) {

    }

    public Object tryExtractFiledValue(final Field field, final Object query) {
        return CriteriaUtils.tryExtractFiledValue(field, query);
    }

    public String tryTranslateToColumnName(final Field field, final NamingStrategy strategy) {
        return CriteriaUtils.tryTranslateToColumnName(field, strategy);
    }
}
