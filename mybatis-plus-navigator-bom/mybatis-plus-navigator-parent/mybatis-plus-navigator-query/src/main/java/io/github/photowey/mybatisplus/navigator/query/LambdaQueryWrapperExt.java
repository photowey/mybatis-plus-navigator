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
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.github.photowey.mybatisplus.navigator.annotation.symbol.Nullable;

import java.util.Objects;

/**
 * {@code LambdaQueryWrapperExt}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/17
 */
public class LambdaQueryWrapperExt<T> extends LambdaQueryWrapper<T> {

    public <V> LambdaQueryWrapperExt<T> eqIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.eq(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> eq(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.eq(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> neIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ne(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> ne(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ne(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> gtIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.gt(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> gt(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.gt(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> geIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ge(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> ge(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ge(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> ltIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.lt(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> lt(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.lt(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> leIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.le(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> le(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.le(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaQueryWrapperExt<T> likeIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.like(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> like(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.like(function, value);
    }

    public <V> LambdaQueryWrapperExt<T> likeLeftIfPresent(SFunction<T, V> function, @Nullable V value) {
        return this.likeIfPresent(function, value, SqlLike.LEFT);
    }

    public <V> LambdaQueryWrapperExt<T> likeLeft(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.likeLeft(function, value);
    }

    public <V> LambdaQueryWrapperExt<T> likeRightIfPresent(SFunction<T, V> function, @Nullable V value) {
        return this.likeIfPresent(function, value, SqlLike.RIGHT);
    }

    public <V> LambdaQueryWrapperExt<T> likeRight(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.likeRight(function, value);
    }

    public <V> LambdaQueryWrapperExt<T> likeIfPresent(SFunction<T, V> function, @Nullable V value, SqlLike sqlLike) {
        if (Objects.isNull(sqlLike)) {
            return this.likeIfPresent(function, value);
        }
        switch (sqlLike) {
            case LEFT:
                return (LambdaQueryWrapperExt<T>) super.likeLeft(ObjectUtils.isNotEmpty(value), function, value);
            case RIGHT:
                return (LambdaQueryWrapperExt<T>) super.likeRight(ObjectUtils.isNotEmpty(value), function, value);
            default:
                return this.likeIfPresent(function, value);
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
}