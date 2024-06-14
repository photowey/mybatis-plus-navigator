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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.github.photowey.mybatisplus.navigator.annotation.symbol.Emptable;
import io.github.photowey.mybatisplus.navigator.annotation.symbol.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * {@code LambdaQueryWrapperExt}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/17
 */
public class LambdaQueryWrapperExt<T> extends LambdaQueryWrapper<T> {

    public <V> LambdaQueryWrapperExt<T> eqIf(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.eq(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> eq(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.eq(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> neIf(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ne(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> ne(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ne(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> gtIf(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.gt(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> gt(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.gt(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> geIf(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ge(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> ge(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ge(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> ltIf(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.lt(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> lt(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.lt(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> leIf(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.le(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> le(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.le(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> likeIf(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.like(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> like(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.like(function, value);
    }

    public <V> LambdaQueryWrapperExt<T> likeLeftIf(SFunction<T, V> function, @Nullable V value) {
        return this.likeIf(function, value, SqlLike.LEFT);
    }

    public <V> LambdaQueryWrapperExt<T> likeLeft(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.likeLeft(function, value);
    }

    public <V> LambdaQueryWrapperExt<T> likeRightIf(SFunction<T, V> function, @Nullable V value) {
        return this.likeIf(function, value, SqlLike.RIGHT);
    }

    public <V> LambdaQueryWrapperExt<T> likeRight(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.likeRight(function, value);
    }

    public <V> LambdaQueryWrapperExt<T> likeIf(SFunction<T, V> function, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.likeIf(function, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (LambdaQueryWrapperExt<T>) super.likeLeft(ObjectUtils.isNotEmpty(value), function, value);
            case RIGHT:
                return (LambdaQueryWrapperExt<T>) super.likeRight(ObjectUtils.isNotEmpty(value), function, value);
            default:
                return this.likeIf(function, value);
        }
    }

    public <V> LambdaQueryWrapperExt<T> like(SFunction<T, V> function, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.like(function, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (LambdaQueryWrapperExt<T>) super.likeLeft(function, value);
            case RIGHT:
                return (LambdaQueryWrapperExt<T>) super.likeRight(function, value);
            default:
                return this.like(function, value);
        }
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> notLikeIf(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.notLike(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> notLike(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.notLike(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> inIf(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaQueryWrapperExt<T>) super.in(ObjectUtils.isNotEmpty(values), function, values);
    }

    public <V> LambdaQueryWrapperExt<T> in(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaQueryWrapperExt<T>) super.in(function, values);
    }

    public <V> LambdaQueryWrapperExt<T> inIf(SFunction<T, V> function, @Nullable @Emptable V... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (LambdaQueryWrapperExt<T>) super.in(function, values);
        }

        return this;
    }

    public <V> LambdaQueryWrapperExt<T> in(SFunction<T, V> function, @Nullable @Emptable V... values) {
        return (LambdaQueryWrapperExt<T>) super.in(function, values);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> notInIf(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaQueryWrapperExt<T>) super.notIn(ObjectUtils.isNotEmpty(values), function, values);
    }

    public <V> LambdaQueryWrapperExt<T> notIn(SFunction<T, V> function, @Nullable @Emptable Collection<V> values) {
        return (LambdaQueryWrapperExt<T>) super.notIn(function, values);
    }

    public <V> LambdaQueryWrapperExt<T> notInIf(SFunction<T, V> function, @Nullable @Emptable V... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (LambdaQueryWrapperExt<T>) super.notIn(function, values);
        }

        return this;
    }

    public <V> LambdaQueryWrapperExt<T> notIn(SFunction<T, V> function, @Nullable @Emptable V... values) {
        return (LambdaQueryWrapperExt<T>) super.notIn(function, values);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> betweenIf(SFunction<T, V> function, @Nullable V from, @Nullable V to) {
        if (from != null && to != null) {
            return (LambdaQueryWrapperExt<T>) super.between(function, from, to);
        }
        if (from != null) {
            return (LambdaQueryWrapperExt<T>) super.ge(function, from);
        }
        if (to != null) {
            return (LambdaQueryWrapperExt<T>) super.le(function, to);
        }

        return this;
    }

    public <V> LambdaQueryWrapperExt<T> between(SFunction<T, V> function, @Nullable V from, @Nullable V to) {
        return (LambdaQueryWrapperExt<T>) super.between(function, from, to);
    }

    // ----------------------------------------------------------------

    @SafeVarargs
    public final LambdaQueryWrapperExt<T> selectx(SFunction<T, ?>... columns) {
        super.select(columns);

        return this;
    }

    @Override
    public LambdaQueryWrapperExt<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        super.select(entityClass, predicate);

        return this;
    }

    // ----------------------------------------------------------------

    public LambdaQueryWrapperExt<T> asc(SFunction<T, ?> column) {
        return this.asc(true, column);
    }

    public LambdaQueryWrapperExt<T> asc(boolean condition, SFunction<T, ?> column) {
        super.orderByAsc(condition, column);

        return this;
    }

    public LambdaQueryWrapperExt<T> asc(List<SFunction<T, ?>> columns) {
        return this.asc(true, columns);
    }

    public LambdaQueryWrapperExt<T> asc(boolean condition, List<SFunction<T, ?>> columns) {
        super.orderByAsc(condition, columns);

        return this;
    }

    @SafeVarargs
    public final LambdaQueryWrapperExt<T> asc(SFunction<T, ?> column, SFunction<T, ?>... columns) {
        return this.asc(true, column, columns);
    }

    @SafeVarargs
    public final LambdaQueryWrapperExt<T> asc(boolean condition, SFunction<T, ?> column, SFunction<T, ?>... columns) {
        super.orderByAsc(condition, column, columns);

        return this;
    }

    // ----------------------------------------------------------------

    public LambdaQueryWrapperExt<T> desc(SFunction<T, ?> column) {
        return this.desc(true, column);
    }

    public LambdaQueryWrapperExt<T> desc(boolean condition, SFunction<T, ?> column) {
        super.orderByDesc(condition, column);

        return this;
    }

    public LambdaQueryWrapperExt<T> desc(List<SFunction<T, ?>> columns) {
        return this.desc(true, columns);
    }

    public LambdaQueryWrapperExt<T> desc(boolean condition, List<SFunction<T, ?>> columns) {
        super.orderByDesc(condition, columns);

        return this;
    }

    @SafeVarargs
    public final LambdaQueryWrapperExt<T> desc(SFunction<T, ?> column, SFunction<T, ?>... columns) {
        return this.desc(true, column, columns);
    }

    @SafeVarargs
    public final LambdaQueryWrapperExt<T> desc(boolean condition, SFunction<T, ?> column, SFunction<T, ?>... columns) {
        super.orderByDesc(condition, column, columns);

        return this;
    }

    // ----------------------------------------------------------------

    /**
     * LIMIT 1
     * |- wrapper.last("LIMIT 1")
     *
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public LambdaQueryWrapperExt<T> limitOne() {
        return this.limit(1);
    }

    /**
     * LIMIT ${limit}
     * |- wrapper.last("LIMIT ${limit}")
     *
     * @return {@link LambdaQueryWrapperExt<T>}
     */
    public LambdaQueryWrapperExt<T> limit(int limit) {
        if (limit <= 0) {
            throw new MybatisPlusException("Invalid parameter 'limit'");
        }

        super.last(String.format("LIMIT %d", limit));

        return this;
    }

    public LambdaQueryWrapperExt<T> limit(long limit) {
        if (limit <= 0) {
            throw new MybatisPlusException("Invalid parameter 'limit'");
        }

        super.last(String.format("LIMIT %d", limit));

        return this;
    }

    // ----------------------------------------------------------------

    public LambdaQueryWrapperExt<T> thiz(Consumer<LambdaQueryWrapperExt<T>> fx) {
        return this.thiz(true, fx);
    }

    public LambdaQueryWrapperExt<T> thiz(boolean condition, Consumer<LambdaQueryWrapperExt<T>> fx) {
        if (condition) {
            fx.accept(this);
        }

        return this;
    }

    // ----------------------------------------------------------------
}