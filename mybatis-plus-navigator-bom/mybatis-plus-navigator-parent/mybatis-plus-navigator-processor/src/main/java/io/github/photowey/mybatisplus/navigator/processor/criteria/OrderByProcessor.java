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
import io.github.photowey.mybatisplus.navigator.annotation.query.OrderBy;
import io.github.photowey.mybatisplus.navigator.core.enums.SortingMechanism;
import io.github.photowey.mybatisplus.navigator.core.enums.SortingOrder;
import io.github.photowey.mybatisplus.navigator.processor.annotation.component.criteria.CriteriaProcessor;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * {@code OrderByProcessor}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/14
 */
@CriteriaProcessor(criteria = OrderBy.class)
public class OrderByProcessor<QUERY extends AbstractQuery<ENTITY>, ENTITY>
        extends AbstractCriteriaAnnotationProcessorAdaptor<OrderBy, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, OrderBy annotation) {
        String value = (String) this.tryExtractFiledValue(field, query);
        if (SortingMechanism.DYNAMIC.equals(annotation.mechanism()) && this.isEmpty(value)) {
            return true;
        }

        if (SortingMechanism.DYNAMIC.equals(annotation.mechanism())) {
            return this.handleDynamic(queryWrapper, field, query, annotation, value);
        }

        return this.handleStatic(queryWrapper, field, query, annotation);
    }

    private boolean handleDynamic(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, OrderBy annotation, String column) {
        SortingOrder order = annotation.order();
        if (Objects.requireNonNull(order) == SortingOrder.DESC) {
            queryWrapper.orderByDesc(column);
        } else {
            queryWrapper.orderByAsc(column);
        }

        return true;
    }

    private boolean handleStatic(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, OrderBy annotation) {
        String column = annotation.alias();
        if (this.isEmpty(column)) {
            column = this.tryTranslateToColumnName(field, annotation.naming());
        }

        if (this.isNotEmpty(column)) {
            SortingOrder order = annotation.order();
            if (Objects.requireNonNull(order) == SortingOrder.DESC) {
                queryWrapper.orderByDesc(column);
            } else {
                queryWrapper.orderByAsc(column);
            }
        }

        return true;
    }
}
