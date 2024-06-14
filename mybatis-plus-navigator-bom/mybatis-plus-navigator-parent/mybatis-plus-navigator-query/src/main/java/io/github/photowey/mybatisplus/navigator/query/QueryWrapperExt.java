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
import io.github.photowey.mybatisplus.navigator.annotation.symbol.Emptyable;
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

    public <V> QueryWrapperExt<T> eqIf(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.eq(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> eq(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.eq(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> neIf(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ne(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> ne(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ne(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> gtIf(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.gt(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> gt(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.gt(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> geIf(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ge(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> ge(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.ge(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> ltIf(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.lt(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> lt(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.lt(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> leIf(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.le(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> le(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.le(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> likeIf(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.like(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> like(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.like(column, value);
    }

    public <V> QueryWrapperExt<T> likeLeftIf(String column, @Nullable V value) {
        return this.likeIf(column, value, SqlLike.LEFT);
    }

    public <V> QueryWrapperExt<T> likeLeft(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.likeLeft(column, value);
    }

    public <V> QueryWrapperExt<T> likeRightIf(String column, @Nullable V value) {
        return this.likeIf(column, value, SqlLike.RIGHT);
    }

    public <V> QueryWrapperExt<T> likeRight(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.likeRight(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> likeIf(String column, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.likeIf(column, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (QueryWrapperExt<T>) super.likeLeft(ObjectUtils.isNotEmpty(value), column, value);
            case RIGHT:
                return (QueryWrapperExt<T>) super.likeRight(ObjectUtils.isNotEmpty(value), column, value);
            default:
                return this.likeIf(column, value);
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

    public <V> QueryWrapperExt<T> notLikeIf(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.notLike(ObjectUtils.isNotEmpty(value), column, value);
    }

    public <V> QueryWrapperExt<T> notLike(String column, @Nullable V value) {
        return (QueryWrapperExt<T>) super.notLike(column, value);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> inIf(String column, @Emptyable Collection<V> values) {
        return (QueryWrapperExt<T>) super.in(CollectionUtils.isNotEmpty(values), column, values);
    }

    public <V> QueryWrapperExt<T> in(String column, @Emptyable Collection<V> values) {
        return (QueryWrapperExt<T>) super.in(column, values);
    }

    public <V> QueryWrapperExt<T> inIf(String column, @Emptyable V... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            return (QueryWrapperExt<T>) super.in(column, values);
        }

        return this;
    }

    @SafeVarargs
    public final <V> QueryWrapperExt<T> in(String column, @Emptyable V... values) {
        return (QueryWrapperExt<T>) super.in(column, values);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> notInIf(String column, @Emptyable Collection<V> values) {
        return (QueryWrapperExt<T>) super.notIn(CollectionUtils.isNotEmpty(values), column, values);
    }

    public <V> QueryWrapperExt<T> notIn(String column, @Emptyable Collection<V> values) {
        return (QueryWrapperExt<T>) super.notIn(column, values);
    }

    public <V> QueryWrapperExt<T> notInIf(String column, @Emptyable V... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            return (QueryWrapperExt<T>) super.notIn(column, values);
        }

        return this;
    }

    public <V> QueryWrapperExt<T> notIn(String column, @Emptyable V... values) {
        return (QueryWrapperExt<T>) super.notIn(column, values);
    }

    // ----------------------------------------------------------------

    public <V> QueryWrapperExt<T> betweenIf(String column, @Nullable V from, @Nullable V to) {
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

    @Override
    public QueryWrapperExt<T> select(String... columns) {
        super.select(columns);

        return this;
    }

    @Override
    public QueryWrapperExt<T> select(List<String> columns) {
        super.select(columns);

        return this;
    }

    // ----------------------------------------------------------------

    public QueryWrapperExt<T> asc(String column) {
        return this.asc(true, column);
    }

    public QueryWrapperExt<T> asc(boolean condition, String column) {
        super.orderByAsc(condition, column);

        return this;
    }

    public QueryWrapperExt<T> asc(List<String> columns) {
        return this.asc(true, columns);
    }

    public QueryWrapperExt<T> asc(boolean condition, List<String> columns) {
        super.orderByAsc(condition, columns);

        return this;
    }

    public QueryWrapperExt<T> asc(String column, String... columns) {
        return this.asc(true, column, columns);
    }

    public QueryWrapperExt<T> asc(boolean condition, String column, String... columns) {
        super.orderByAsc(condition, column, columns);

        return this;
    }

    // ----------------------------------------------------------------

    public QueryWrapperExt<T> desc(String column) {
        return this.desc(true, column);
    }

    public QueryWrapperExt<T> desc(boolean condition, String column) {
        super.orderByDesc(condition, column);

        return this;
    }

    public QueryWrapperExt<T> desc(List<String> columns) {
        return this.desc(true, columns);
    }

    public QueryWrapperExt<T> desc(boolean condition, List<String> columns) {
        super.orderByDesc(condition, columns);

        return this;
    }

    public QueryWrapperExt<T> desc(String column, String... columns) {
        return this.desc(true, column, columns);
    }

    public QueryWrapperExt<T> desc(boolean condition, String column, String... columns) {
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

    public QueryWrapperExt<T> limit(long limit) {
        if (limit <= 0) {
            throw new MybatisPlusException("Invalid parameter 'limit'");
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

    // ----------------------------------------------------------------
}
