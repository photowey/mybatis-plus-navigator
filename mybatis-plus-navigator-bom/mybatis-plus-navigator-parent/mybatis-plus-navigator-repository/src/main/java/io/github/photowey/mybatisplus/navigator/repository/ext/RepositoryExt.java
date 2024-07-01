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
package io.github.photowey.mybatisplus.navigator.repository.ext;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.github.photowey.mybatisplus.navigator.annotation.symbol.NotNull;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;
import io.github.photowey.mybatisplus.navigator.query.QueryWrapperExt;

import java.util.function.Consumer;

/**
 * {@code RepositoryExt}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/06/24
 */
public interface RepositoryExt<T> extends BaseMapper<T> {

    default IPage<T> selectPage(AbstractQuery<T> query) {
        QueryWrapperExt<T> wrapper = query.tryVisitQueryWrapperExt();
        IPage<T> page = query.populatePage();
        return this.selectPage(page, wrapper);
    }

    default IPage<T> selectPage(AbstractQuery<T> query, Consumer<QueryWrapperExt<T>> callback) {
        QueryWrapperExt<T> wrapper = query.tryVisitQueryWrapperExt();
        callback.accept(wrapper);

        IPage<T> page = query.populatePage();
        return this.selectPage(page, wrapper);
    }

    // ----------------------------------------------------------------

    default <V> T selectOne(String column, @NotNull V value) {
        this.checkNull(column, value);

        return this.selectOne(this.createQueryWrapper().eq(column, value));
    }

    default T selectOne(Consumer<QueryWrapper<T>> callback) {
        QueryWrapper<T> wrapper = this.createQueryWrapper();
        callback.accept(wrapper);

        return this.selectOne(wrapper);
    }

    default <V> T selectOne(String column, @NotNull V value, Consumer<QueryWrapper<T>> callback) {
        this.checkNull(column, value);

        QueryWrapper<T> wrapper = this.createQueryWrapper().eq(column, value);
        callback.accept(wrapper);

        return this.selectOne(wrapper);
    }

    default <V> T selectOne(SFunction<T, ?> function, @NotNull V value) {
        this.checkNull(value);

        return this.selectOne(this.createLambdaQueryWrapper().eq(function, value));
    }

    // ----------------------------------------------------------------

    default <V> void checkNull(String column, V value) {
        if (null == value) {
            throw new NullPointerException(String.format("The parameter:[%s] value can't be null", column));
        }
    }

    default <V> void checkNull(V value) {
        if (null == value) {
            throw new NullPointerException("The parameter:[value] can't be null");
        }
    }

    // ----------------------------------------------------------------

    default QueryWrapper<T> createQueryWrapper() {
        return new QueryWrapper<>();
    }

    default LambdaQueryWrapper<T> createLambdaQueryWrapper() {
        return new LambdaQueryWrapper<>();
    }
}
