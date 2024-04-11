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
import io.github.photowey.mybatisplus.navigator.annotation.query.Timestamp;
import io.github.photowey.mybatisplus.navigator.core.enums.Operator;
import io.github.photowey.mybatisplus.navigator.processor.annotation.component.criteria.CriteriaProcessor;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;

/**
 * {@code TimestampProcessor}
 * <p>
 * Examples:
 * <pre>
 * {@literal  @}Timestamp(alias = "create_time", compare = Operator.GE)
 * {@literal  @}Timestamp(alias = "create_time", compare = Operator.GE, clazz = LocalDateTime.class)
 * {@literal  @}Timestamp(compare = Operator.GE, clazz = LocalDateTime.class, naming = NamingStrategy.SNAKE_CASE)
 *
 * {@literal  @}Timestamp(alias = "create_time", compare = Operator.GE, clazz = Date.class)
 * {@literal  @}Timestamp(compare = Operator.GE, clazz = Date.class, naming = NamingStrategy.SNAKE_CASE)
 * </pre>
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/04/12
 */
@CriteriaProcessor(criteria = Timestamp.class)
public class TimestampProcessor<QUERY extends AbstractQuery<ENTITY>, ENTITY>
        extends AbstractCriteriaAnnotationProcessorAdaptor<Timestamp, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Timestamp annotation) {
        return this.onProcess(field, query, annotation::alias, annotation::naming, (column, value) -> {
            Long timeStamp = (Long) value;
            Operator compare = annotation.compare();
            Class<?> clazz = annotation.clazz();
            Object time = this.transferTime(timeStamp, clazz);

            this.doWrapTime(queryWrapper, compare, column, time);
        });
    }
}
