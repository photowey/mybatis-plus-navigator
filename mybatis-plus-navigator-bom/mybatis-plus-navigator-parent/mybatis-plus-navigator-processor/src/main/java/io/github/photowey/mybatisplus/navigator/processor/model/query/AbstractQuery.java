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
package io.github.photowey.mybatisplus.navigator.processor.model.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.photowey.mybatisplus.navigator.core.model.pagination.AbstractPagination;
import io.github.photowey.mybatisplus.navigator.core.util.PaginationUtils;
import io.github.photowey.mybatisplus.navigator.processor.visitor.CriteriaQueryVisitor;
import io.github.photowey.mybatisplus.navigator.query.QueryWrapperExt;

/**
 * {@code AbstractQuery}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/03/31
 */
public abstract class AbstractQuery<T> extends AbstractPagination {

    public QueryWrapper<T> tryVisitQueryWrapper() {
        return CriteriaQueryVisitor.visit(this, new QueryWrapper<>());
    }

    public QueryWrapperExt<T> tryVisitQueryWrapperExt() {
        return CriteriaQueryVisitor.visitExt(this, new QueryWrapperExt<>());
    }

    public IPage<T> populatePage() {
        return PaginationUtils.toPage(this);
    }
}