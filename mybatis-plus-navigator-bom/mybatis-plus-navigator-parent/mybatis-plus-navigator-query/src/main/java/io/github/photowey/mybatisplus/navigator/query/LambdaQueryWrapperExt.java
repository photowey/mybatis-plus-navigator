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
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.github.photowey.mybatisplus.navigator.annotation.symbol.Nullable;

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

    public <V> LambdaQueryWrapperExt<T> neIfPresent(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ne(ObjectUtils.isNotEmpty(value), function, value);
    }

    public <V> LambdaQueryWrapperExt<T> ne(SFunction<T, V> function, @Nullable V value) {
        return (LambdaQueryWrapperExt<T>) super.ne(function, value);
    }
}