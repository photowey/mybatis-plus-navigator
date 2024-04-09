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
import io.github.photowey.mybatisplus.navigator.core.enums.NamingStrategy;
import io.github.photowey.mybatisplus.navigator.core.exception.NavigatorRuntimeException;
import io.github.photowey.mybatisplus.navigator.core.util.CriteriaUtils;
import io.github.photowey.mybatisplus.navigator.processor.handler.ConditionHandler;
import io.github.photowey.mybatisplus.navigator.processor.holder.ApplicationContextHolder;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * {@code AbstractCriteriaAnnotationProcessorAdaptor}
 *
 * @author photowey
 * @date 2024/04/02
 * @since 1.0.0
 */
public abstract class AbstractCriteriaAnnotationProcessorAdaptor<
        A extends Annotation,
        QUERY extends AbstractQuery<?>,
        WRAPPER extends QueryWrapper<ENTITY>,
        ENTITY>
        implements CriteriaAnnotationProcessor<A, QUERY, WRAPPER, ENTITY> {

    public boolean onProcess(Field field, QUERY query, Supplier<String> as, Supplier<NamingStrategy> ns, BiConsumer<String, Object> expr) {
        final Object value = this.tryExtractFiledValue(field, query);
        if (this.isEmpty(value)) {
            return true;
        }

        String column = as.get();
        if (this.isEmpty(column)) {
            column = this.tryTranslateToColumnName(field, ns.get());
        }
        assert column != null;
        expr.accept(column, value);

        return true;
    }

    public boolean onProcessCollection(Field field, QUERY query, Supplier<String> as, Supplier<NamingStrategy> ns, BiConsumer<String, Collection<?>> expr) {
        final Collection<?> values = this.tryExtractFiledValues(field, query);
        if (this.isEmpty(values)) {
            return true;
        }

        String column = as.get();
        if (this.isEmpty(column)) {
            column = this.tryTranslateToColumnName(field, ns.get());
        }
        assert column != null;
        expr.accept(column, values);

        return true;
    }

    public <T> boolean isNotEmpty(T value) {
        return !this.isEmpty(value);
    }

    public <T> boolean isEmpty(T value) {
        return ObjectUtils.isEmpty(value);
    }

    protected ConditionHandler tryAcquireConditionHandler(String handler) {
        ApplicationContext applicationContext = ApplicationContextHolder.INSTANCE.applicationContext();
        try {
            return applicationContext.getBean(handler, ConditionHandler.class);
        } catch (BeansException e) {
            throw new NavigatorRuntimeException("navigator: the handler:[%s] is invalid.", handler);
        }
    }

    protected Object tryExtractFiledValue(final Field field, final Object query) {
        return CriteriaUtils.tryExtractFiledValue(field, query);
    }

    protected Collection<?> tryExtractFiledValues(final Field field, final Object query) {
        Object value = CriteriaUtils.tryExtractFiledValue(field, query);
        if (value instanceof Collection) {
            return (Collection<?>) value;
        }

        throw new NavigatorRuntimeException("navigator: the field:[%s] must be of type Collection", field.getName());
    }

    protected String tryTranslateToColumnName(final Field field, final NamingStrategy strategy) {
        return CriteriaUtils.tryTranslateToColumnName(field, strategy);
    }
}
