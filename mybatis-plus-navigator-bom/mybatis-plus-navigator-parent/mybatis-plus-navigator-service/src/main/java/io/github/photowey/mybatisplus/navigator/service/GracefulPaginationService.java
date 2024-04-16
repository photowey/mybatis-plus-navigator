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
package io.github.photowey.mybatisplus.navigator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code GracefulPaginationService}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/16
 */
public interface GracefulPaginationService<T> extends CounterService<T> {

    default <Q extends AbstractQuery<T>> void tryThresholdSizeEnabled(Q query) {
        if (null == query) {
            return;
        }

        query.tryThresholdSizeEnabled();
    }

    default <Q extends AbstractQuery<T>> IPage<T> tryDynamicPage(Q query) {
        if (null == query) {
            return AbstractQuery.populateDefaultPage();
        }

        return query.populatePage();
    }

    default <Q extends AbstractQuery<T>> List<T> tryListGraceful(Q query) {
        long current = 1L;
        List<T> tts = new ArrayList<>();
        this.tryThresholdSizeEnabled(query);

        while (true) {
            query.setCurrent(current);
            IPage<T> page = this.tryDynamicPage(query);
            List<T> records = page.getRecords();
            if (ObjectUtils.isEmpty(records)) {
                break;
            }

            if (ObjectUtils.isNotEmpty(records)) {
                tts.addAll(records);
            }

            if (page.getCurrent() == page.getPages()) {
                break;
            }
            current++;
        }

        return tts;
    }
}