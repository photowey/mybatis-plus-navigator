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
package io.github.photowey.mybatisplus.navigator.core.domain.entity;

import java.time.LocalDateTime;

/**
 * {@code RootEntity}
 * |- must: id | create_time | update_time
 *
 * @author photowey
 * @version 1.0.0
 * @see <a href="https://developer.aliyun.com/ebook/394">ebook</a>
 * @since 2024/03/19
 */
public interface RootEntity extends Root {

    // create_time | update_time | create_by | update_by

    // ---------------------------------------------------------------- Setter

    default void setCreateTime(LocalDateTime createTime) {

    }

    default void setUpdateTime(LocalDateTime updateTime) {

    }

    default void setCreateBy(Long createBy) {

    }

    default void setUpdateBy(Long updateBy) {

    }

    // ---------------------------------------------------------------- Getter

    default LocalDateTime getCreateTime() {
        return null;
    }

    default LocalDateTime getUpdateTime() {
        return null;
    }

    default Long getCreateBy() {
        return null;
    }

    default Long getUpdateBy() {
        return null;
    }

}
