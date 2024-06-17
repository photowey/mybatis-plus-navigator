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

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.github.photowey.mybatisplus.navigator.annotation.symbol.Nullable;

import java.util.function.Consumer;

/**
 * {@code LambdaUpdateWrapperExt}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/17
 */
public class LambdaUpdateWrapperExt<T> extends LambdaUpdateWrapper<T> {

    public <V> LambdaUpdateWrapperExt<T> eqIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.eq(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> eq(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.eq(function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> neIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.ne(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaUpdateWrapperExt<T> ne(SFunction<T, V> function, @Nullable V value) {
        return (LambdaUpdateWrapperExt<T>) super.ne(function, value);
    }

    // ----------------------------------------------------------------

    public <V> LambdaUpdateWrapperExt<T> setIfPresent(SFunction<T, V> column, V value) {
        super.set(ObjectUtils.isNotEmpty(value), column, value);

        return this;
    }

    public <V> LambdaUpdateWrapperExt<T> setIfPresent(SFunction<T, V> column, V value, String mapping) {
        super.set(ObjectUtils.isNotEmpty(value), column, value, mapping);

        return this;
    }

    // ----------------------------------------------------------------

    @Override
    public LambdaUpdateWrapperExt<T> set(SFunction<T, ?> column, Object value) {
        super.set(column, value);

        return this;
    }

    @Override
    public LambdaUpdateWrapperExt<T> set(boolean condition, SFunction<T, ?> column, Object value) {
        super.set(condition, column, value);

        return this;
    }

    @Override
    public LambdaUpdateWrapperExt<T> set(SFunction<T, ?> column, Object value, String mapping) {
        super.set(column, value, mapping);

        return this;
    }

    @Override
    public LambdaUpdateWrapperExt<T> set(boolean condition, SFunction<T, ?> column, Object value, String mapping) {
        super.set(condition, column, value, mapping);

        return this;
    }

    // ----------------------------------------------------------------

    public LambdaUpdateWrapperExt<T> thiz(Consumer<LambdaUpdateWrapperExt<T>> fx) {
        return this.thiz(true, fx);
    }

    public LambdaUpdateWrapperExt<T> thiz(boolean condition, Consumer<LambdaUpdateWrapperExt<T>> fx) {
        if (condition) {
            fx.accept(this);
        }

        return this;
    }

}