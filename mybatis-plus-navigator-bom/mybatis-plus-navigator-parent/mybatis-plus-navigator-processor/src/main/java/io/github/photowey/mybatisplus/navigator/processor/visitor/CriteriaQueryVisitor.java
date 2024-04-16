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
package io.github.photowey.mybatisplus.navigator.processor.visitor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.photowey.mybatisplus.navigator.processor.criteria.CriteriaAnnotationProcessor;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;
import io.github.photowey.mybatisplus.navigator.processor.parser.CriteriaFieldParser;
import io.github.photowey.mybatisplus.navigator.processor.registry.CriteriaRegistry;
import io.github.photowey.mybatisplus.navigator.query.QueryWrapperExt;

/**
 * {@code CriteriaQueryVisitor}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/16
 */
public final class CriteriaQueryVisitor {

    public static <QUERY extends AbstractQuery<ENTITY>, ENTITY> QueryWrapper<ENTITY> visit(final QUERY query, QueryWrapper<ENTITY> queryWrapper) {
        CriteriaFieldParser.getInstance().traversalQuery(query, (field, annotation) -> {
            final CriteriaAnnotationProcessor processorCached = CriteriaRegistry.tryFindProcessor(annotation.annotationType());
            assert processorCached != null;
            return processorCached.process(queryWrapper, field, query, annotation);
        });

        return queryWrapper;
    }

    public static <QUERY extends AbstractQuery, ENTITY> QueryWrapperExt<ENTITY> visitExt(final QUERY query, QueryWrapperExt<ENTITY> queryWrapper) {
        CriteriaFieldParser.getInstance().traversalQuery(query, (field, annotation) -> {
            final CriteriaAnnotationProcessor processorCached = CriteriaRegistry.tryFindProcessor(annotation.annotationType());
            assert processorCached != null;
            return processorCached.process(queryWrapper, field, query, annotation);
        });

        return queryWrapper;
    }
}