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
package io.github.photowey.mybatisplus.navigator.processor.criteria;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.photowey.mybatisplus.navigator.annotation.query.Exists;
import io.github.photowey.mybatisplus.navigator.processor.annotation.component.criteria.CriteriaProcessor;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * {@code ExistsProcessor}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/10
 */
@CriteriaProcessor(criteria = Exists.class)
public class ExistsProcessor<QUERY extends AbstractQuery<ENTITY>, ENTITY>
        extends AbstractCriteriaAnnotationProcessorAdaptor<Exists, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Exists annotation) {
        final Object value = this.tryExtractFiledValue(field, query);
        if (this.isEmpty(value)) {
            String existsSql = annotation.existsSql();
            if (this.isNotEmpty(existsSql)) {
                queryWrapper.exists(existsSql);
            }

            return true;
        }

        String existsSql = annotation.existsSql();
        if (this.isNotEmpty(existsSql)) {
            if (value instanceof Collection) {
                queryWrapper.exists(existsSql, ((Collection<?>) value).toArray());

                return true;
            }

            queryWrapper.exists(existsSql, value);
        }

        return true;
    }
}
