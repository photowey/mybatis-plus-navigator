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
package io.github.photowey.mybatisplus.navigator.core.model.pagination;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.photowey.mybatisplus.navigator.core.domain.sorting.SortingTerm;

import java.util.HashSet;
import java.util.Set;

/**
 * {@code Pagination}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/03/31
 */
public interface Pagination {

    long DEFAULT_CURRENT = 1;
    long DEFAULT_SIZE = 10;

    long THRESHOLD_CURRENT = 1;
    long THRESHOLD_SIZE = 100;

    default Long getCurrent() {
        return DEFAULT_CURRENT;
    }

    default Long getSize() {
        return DEFAULT_SIZE;
    }

    default Set<SortingTerm> getSortingTerms() {
        return new HashSet<>();
    }

    default <T> Page<T> toPage() {
        return new Page<>(this.getCurrent(), this.getSize());
    }
}
