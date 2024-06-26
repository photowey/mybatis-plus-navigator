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
package io.github.photowey.mybatisplus.navigator.annotation.query;

import io.github.photowey.mybatisplus.navigator.core.enums.NamingStrategy;
import io.github.photowey.mybatisplus.navigator.core.enums.SortingMechanism;
import io.github.photowey.mybatisplus.navigator.core.enums.SortingOrder;

import java.lang.annotation.*;

/**
 * {@code MultiOrderBy}
 * |- ORDER BY A,B ASC, Z DESC
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/15
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiOrderBy {

    /**
     * The properties
     *
     * @return the properties of {@code Database} Entity.
     */
    String[] properties() default {};

    SortingOrder order() default SortingOrder.ASC;

    /**
     * The sorting mechanism.
     *
     * @return the sorting mechanism.
     */
    SortingMechanism mechanism() default SortingMechanism.STATIC;

    /**
     * The {@code Database} column name strategy.
     *
     * @return the {@code Database} column name strategy.
     */
    NamingStrategy naming() default NamingStrategy.SNAKE_CASE;
}