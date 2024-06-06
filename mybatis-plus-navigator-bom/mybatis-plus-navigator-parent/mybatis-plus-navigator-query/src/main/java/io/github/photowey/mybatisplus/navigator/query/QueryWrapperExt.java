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
package io.github.photowey.mybatisplus.navigator.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import io.github.photowey.mybatisplus.navigator.annotation.symbol.Emptable;
import io.github.photowey.mybatisplus.navigator.annotation.symbol.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@code QueryWrapperExt}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/17
 */
public class QueryWrapperExt<T> extends QueryWrapper<T> {

    public <V> QueryWrapperExt<T> eqIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.eq(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> eq(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.eq(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> neIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ne(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> ne(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ne(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> gtIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.gt(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> gt(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.gt(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> geIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ge(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> ge(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ge(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> ltIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.lt(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> lt(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.lt(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> leIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.le(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> le(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.le(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> likeIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.like(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> like(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.like(column, value);
    }

    public <V> QueryWrapperExt<T> likeLeftIfPresent(String column, @Nullable V value) {
        return this.likeIfPresent(column, value, SqlLike.LEFT);
    }

    public <V> QueryWrapperExt<T> likeLeft(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.likeLeft(column, value);
    }

    public <V> QueryWrapperExt<T> likeRightIfPresent(String column, @Nullable V value) {
        return this.likeIfPresent(column, value, SqlLike.RIGHT);
    }

    public <V> QueryWrapperExt<T> likeRight(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.likeRight(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> likeIfPresent(String column, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.likeIfPresent(column, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (QueryWrapperExt<T>) super.likeLeft(ObjectUtils.isNotEmpty(value), column, value);
            case RIGHT:
                return (QueryWrapperExt<T>) super.likeRight(ObjectUtils.isNotEmpty(value), column, value);
            default:
                return this.likeIfPresent(column, value);
        }
    }

    public <V> QueryWrapperExt<T> like(String column, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.like(column, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (QueryWrapperExt<T>) super.likeLeft(column, value);
            case RIGHT:
                return (QueryWrapperExt<T>) super.likeRight(column, value);
            default:
                return this.like(column, value);
        }
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> notLikeIfPresent(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.notLike(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> notLike(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.notLike(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> inIfPresent(String column, @Nullable @Emptable Collection<V> values) {
        return (QueryWrapperExt<T>) super.in(CollectionUtils.isNotEmpty(values), column, values);
    }

    public <V> QueryWrapperExt<T> in(String column, @Nullable @Emptable Collection<V> values) {
        return (QueryWrapperExt<T>) super.in(column, values);
    }

    public <V> QueryWrapperExt<T> inIfPresent(String column, @Nullable @Emptable V... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            return (QueryWrapperExt<T>) super.in(column, values);
        }

        return this;
    }

    public <V> QueryWrapperExt<T> in(String column, @Nullable @Emptable V... values) {
        return (QueryWrapperExt<T>) super.in(column, values);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> notInIfPresent(String column, @Nullable @Emptable Collection<V> values) {
        return (QueryWrapperExt<T>) super.notIn(CollectionUtils.isNotEmpty(values), column, values);
    }

    public <V> QueryWrapperExt<T> notIn(String column, @Nullable @Emptable Collection<V> values) {
        return (QueryWrapperExt<T>) super.notIn(column, values);
    }

    public <V> QueryWrapperExt<T> notInIfPresent(String column, @Nullable @Emptable V... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            return (QueryWrapperExt<T>) super.notIn(column, values);
        }

        return this;
    }

    public <V> QueryWrapperExt<T> notIn(String column, @Nullable @Emptable V... values) {
        return (QueryWrapperExt<T>) super.notIn(column, values);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> betweenIfPresent(String column, @Nullable V from, @Nullable V to) {
        if (from != null && to != null) {
            return (QueryWrapperExt<T>) super.between(column, from, to);
        }
        if (from != null) {
            return (QueryWrapperExt<T>) super.ge(column, from);
        }
        if (to != null) {
            return (QueryWrapperExt<T>) super.le(column, to);
        }

        return this;
    }

    public <V> QueryWrapperExt<T> between(String column, @Nullable V from, @Nullable V to) {
        return (QueryWrapperExt<T>) super.between(column, from, to);
    }

    // ----------------------------------------------------------------

    public QueryWrapperExt<T> select(String... columns) {
        super.select(columns);

        return this;
    }

    // ----------------------------------------------------------------

    public QueryWrapperExt<T> orderByAsc(String column) {
        return this.orderByAsc(true, column);
    }

    public QueryWrapperExt<T> orderByAsc(boolean condition, String column) {
        super.orderByAsc(condition, column);

        return this;
    }

    public QueryWrapperExt<T> orderByAsc(List<String> columns) {
        return this.orderByAsc(true, columns);
    }

    public QueryWrapperExt<T> orderByAsc(boolean condition, List<String> columns) {
        super.orderByAsc(condition, columns);

        return this;
    }

    public QueryWrapperExt<T> orderByAsc(String column, String... columns) {
        return this.orderByAsc(true, column, columns);
    }

    public QueryWrapperExt<T> orderByAsc(boolean condition, String column, String... columns) {
        super.orderByAsc(condition, column, columns);

        return this;
    }

    // ----------------------------------------------------------------

    public QueryWrapperExt<T> orderByDesc(String column) {
        return this.orderByDesc(true, column);
    }

    public QueryWrapperExt<T> orderByDesc(boolean condition, String column) {
        super.orderByDesc(condition, column);

        return this;
    }

    public QueryWrapperExt<T> orderByDesc(List<String> columns) {
        return this.orderByDesc(true, columns);
    }

    public QueryWrapperExt<T> orderByDesc(boolean condition, List<String> columns) {
        super.orderByDesc(condition, columns);

        return this;
    }

    public QueryWrapperExt<T> orderByDesc(String column, String... columns) {
        return this.orderByDesc(true, column, columns);
    }

    public QueryWrapperExt<T> orderByDesc(boolean condition, String column, String... columns) {
        super.orderByDesc(condition, column, columns);

        return this;
    }

    // ----------------------------------------------------------------

    public QueryWrapperExt<T> limitOne() {
        return this.limit(1);
    }

    public QueryWrapperExt<T> limit(int limit) {
        if (limit <= 0) {
            throw new MybatisPlusException("Invalid limit parameter.");
        }

        super.last(String.format("LIMIT %d", limit));

        return this;
    }

    // ----------------------------------------------------------------

    public QueryWrapperExt<T> thiz(Consumer<QueryWrapperExt<T>> fx) {
        return this.thiz(true, fx);
    }

    public QueryWrapperExt<T> thiz(boolean condition, Consumer<QueryWrapperExt<T>> fx) {
        if (condition) {
            fx.accept(this);
        }

        return this;
    }
}
