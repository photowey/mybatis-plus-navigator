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

/**
 * {@code CreatorEntity}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/07/21
 */
public interface CreatorEntity extends Root {

    // created_by | updated_by

    // ---------------------------------------------------------------- Setter

    default void setCreatedBy(Long createdBy) {}

    default void setUpdatedBy(Long updatedBy) {}

    // ---------------------------------------------------------------- Getter

    default Long getCreatedBy() {
        return null;
    }

    default Long getUpdatedBy() {
        return null;
    }
}
