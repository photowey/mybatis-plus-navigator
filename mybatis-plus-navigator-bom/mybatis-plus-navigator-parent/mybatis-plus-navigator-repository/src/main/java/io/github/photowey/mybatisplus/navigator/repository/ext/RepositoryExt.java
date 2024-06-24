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

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
        QueryWrapperExt<T> queryWrapperExt = query.tryVisitQueryWrapperExt();
        IPage<T> page = query.populatePage();
        return this.selectPage(page, queryWrapperExt);
    }

    default IPage<T> selectPage(AbstractQuery<T> query, Consumer<QueryWrapperExt<T>> callback) {
        QueryWrapperExt<T> queryWrapperExt = query.tryVisitQueryWrapperExt();
        callback.accept(queryWrapperExt);

        IPage<T> page = query.populatePage();
        return this.selectPage(page, queryWrapperExt);
    }

}
