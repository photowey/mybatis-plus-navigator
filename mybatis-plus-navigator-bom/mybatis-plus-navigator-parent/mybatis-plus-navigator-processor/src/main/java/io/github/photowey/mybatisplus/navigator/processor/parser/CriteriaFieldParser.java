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
package io.github.photowey.mybatisplus.navigator.processor.parser;

import io.github.photowey.mybatisplus.navigator.annotation.query.CriteriaQuery;
import io.github.photowey.mybatisplus.navigator.core.callback.FieldCallback;
import io.github.photowey.mybatisplus.navigator.core.constant.InfrasConstants;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code CriteriaFieldParser}
 *
 * @author photowey
 * @date 2024/03/31
 * @since 1.0.0
 */
public class CriteriaFieldParser implements FieldParser {

    /**
     * Usually in a complex system, dozens or hundreds of data tables may be designed,
     * but there may not be many scenarios where there are only single table queries,
     * so the initialCapacity default value is set to 32.
     */
    private static final ConcurrentHashMap<Class<?>, List<Field>> CLASS_FIELD_REF_CACHE =
            new ConcurrentHashMap<>(InfrasConstants.determineSingleTableQueryThreshold());

    private CriteriaFieldParser() {}

    @Override
    public <QUERY extends AbstractQuery<?>> void traversalQuery(QUERY query, FieldCallback callback) {
        if (ObjectUtils.isEmpty(query)) {
            return;
        }
        this.traversalClass(query.getClass(), callback);
    }

    @Override
    public void traversalClass(Class<?> clazz, FieldCallback callback) {
        if (ObjectUtils.isEmpty(clazz)) {
            return;
        }

        List<Field> fields = CLASS_FIELD_REF_CACHE.computeIfAbsent(clazz, this::tryCollectClassFields);
        this.traversalFields(fields, callback);
    }

    @Override
    public void traversalFields(List<Field> fields, FieldCallback callback) {
        if (ObjectUtils.isEmpty(fields)) {
            return;
        }
        fields.forEach(v -> this.traversalField(v, callback));
    }

    @Override
    public void traversalField(Field field, FieldCallback callback) {
        if (ObjectUtils.isEmpty(field)) {
            return;
        }
        final Annotation[] annotations = field.getDeclaredAnnotations();
        if (ObjectUtils.isEmpty(annotations)) {
            return;
        }

        List<Annotation> collected = Stream.of(annotations)
                .filter(v -> v.annotationType().isAnnotationPresent(CriteriaQuery.class))
                .collect(Collectors.toList());
        for (final Annotation annotation : collected) {
            if (!callback.test(field, annotation)) {
                break;
            }
        }
    }

    // ----------------------------------------------------------------

    private List<Field> tryCollectClassFields(Class<?> clazz) {
        List<Field> caches = new ArrayList<>();
        for (Class<?> thisClazz = clazz; thisClazz != Object.class; thisClazz = thisClazz.getSuperclass()) {
            final Field[] fields = thisClazz.getDeclaredFields();
            caches.addAll(Arrays.asList(fields));
        }

        return caches;
    }

    // ----------------------------------------------------------------

    public static class CriteriaFieldParserFactory {
        public static final CriteriaFieldParser INSTANCE = new CriteriaFieldParser();
    }

    public static CriteriaFieldParser getInstance() {
        return CriteriaFieldParserFactory.INSTANCE;
    }

    // ----------------------------------------------------------------
}