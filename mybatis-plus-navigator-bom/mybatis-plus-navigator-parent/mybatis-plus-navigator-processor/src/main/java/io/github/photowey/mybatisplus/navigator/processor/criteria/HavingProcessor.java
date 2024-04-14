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
import io.github.photowey.mybatisplus.navigator.annotation.query.Having;
import io.github.photowey.mybatisplus.navigator.processor.annotation.component.criteria.CriteriaProcessor;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;

/**
 * {@code HavingProcessor}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/14
 */
@CriteriaProcessor(criteria = Having.class)
public class HavingProcessor<QUERY extends AbstractQuery<ENTITY>, ENTITY>
        extends AbstractCriteriaAnnotationProcessorAdaptor<Having, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Having annotation) {
        boolean dynamic = annotation.dynamic();
        if (dynamic) {
            return this.handleDynamic(queryWrapper, field, query, annotation);
        }

        return this.handleStatic(queryWrapper, field, query, annotation);
    }

    private boolean handleDynamic(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Having annotation) {
        final Object value = this.tryExtractFiledValue(field, query);
        if (this.isEmpty(value)) {
            return true;
        }

        String having = annotation.having();
        if (this.isNotEmpty(having)) {
            queryWrapper.having(having, value);
        }

        return true;
    }

    private boolean handleStatic(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Having annotation) {
        String having = annotation.having();
        if (this.isNotEmpty(having)) {
            queryWrapper.having(having);
        }

        return true;
    }
}
