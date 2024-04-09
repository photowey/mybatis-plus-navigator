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
package io.github.photowey.mybatisplus.navigator.core.constant;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
 * {@code InfrasConstants}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/03/20
 */
public interface InfrasConstants {

    interface ConfigKey {
        int SINGLE_TABLE_QUERY_THRESHOLD = 1 << 5;
        String SINGLE_TABLE_QUERY_THRESHOLD_CONFIG_KEY = "io.github.photowey.mybatisplus.navigator.single.table.query.threshold";
    }

    static int determineSingleTableQueryThreshold() {
        String threshold = System.getenv(ConfigKey.SINGLE_TABLE_QUERY_THRESHOLD_CONFIG_KEY);
        if (ObjectUtils.isEmpty(threshold)) {
            threshold = System.getProperty(ConfigKey.SINGLE_TABLE_QUERY_THRESHOLD_CONFIG_KEY);
        }

        return ObjectUtils.isNotEmpty(threshold) ? Integer.parseInt(threshold) : ConfigKey.SINGLE_TABLE_QUERY_THRESHOLD;
    }
}
