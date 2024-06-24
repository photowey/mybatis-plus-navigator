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
package io.github.photowey.mybatisplus.navigator.domain.core.assembler;

import java.util.List;

/**
 * {@code EntityMapper}
 * |- {@code MapStruct}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @see <a href="https://github.com/mapstruct/mapstruct">MapStruct</a>
 * @since 2024/03/19
 */
public interface EntityMapper<D, E> {

    /**
     * To entity.
     *
     * @param dto DTO
     * @return E
     */
    E toEntity(D dto);

    /**
     * To DTO.
     *
     * @param entity Entity
     * @return D
     */
    D toDto(E entity);

    /**
     * To entity.
     *
     * @param dtoList DTO list
     * @return E
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * To DTO.
     *
     * @param entityList Entity list
     * @return D
     */
    List<D> toDto(List<E> entityList);
}
