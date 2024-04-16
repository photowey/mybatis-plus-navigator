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
package io.github.photowey.mybatisplus.navigator.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.photowey.mybatisplus.navigator.core.domain.sorting.SortingItem;
import io.github.photowey.mybatisplus.navigator.core.enums.SortingOrder;
import io.github.photowey.mybatisplus.navigator.core.model.pagination.Pagination;
import io.github.photowey.mybatisplus.navigator.core.thrower.AssertionErrorThrower;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * {@code PaginationUtils}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/16
 */
public final class PaginationUtils {

    private PaginationUtils() {
        AssertionErrorThrower.throwz(PaginationUtils.class);
    }

    public static <T, Q extends Pagination> IPage<T> toPage(Q query) {
        return toPage(query, (x) -> {});
    }

    public static <T, Q extends Pagination> IPage<T> toPage(Q query, Consumer<IPage<T>> callback) {
        Page<T> page = query.toPage();

        Collection<SortingItem> items = query.getSortingItems();
        if (ObjectUtils.isNotEmpty(items)) {
            page.addOrder(items.stream().map(PaginationUtils::sorting).collect(Collectors.toList()));
        }

        if (null != callback) {
            callback.accept(page);
        }

        return page;
    }

    private static OrderItem sorting(SortingItem item) {
        return SortingOrder.ASC.equals(item.getOrder()) ? OrderItem.asc(item.getColumn()) : OrderItem.desc(item.getColumn());
    }
}