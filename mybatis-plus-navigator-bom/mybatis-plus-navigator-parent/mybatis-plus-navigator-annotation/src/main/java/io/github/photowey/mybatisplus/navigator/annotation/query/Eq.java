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

import io.github.photowey.mybatisplus.navigator.core.enums.NamingEnum;

import java.lang.annotation.*;

/**
 * {@code Eq}
 * |- ==
 * <p>
 * # Examples:
 * <pre>
 * public class HelloQuery implements Serializable {
 *
 *     // id = #{id}
 *    {@literal @}Eq
 *     private Long id;
 *
 *     // create_time = #{createdAt}
 *    {@literal @}Eq(alias = "create_time")
 *     private Long createdAt;
 *
 *     // updated_at = ${updatedAt}
 *    {@literal @}Eq(naming = NamingEnum.SNAKE_CASE)
 *     private Long updatedAt;
 * }
 * </pre>
 *
 * @author photowey
 * @date 2024/03/20
 * @since 1.0.0
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Eq {

    /**
     * The alias of the field modified by {@code @Eq} annotation in the database.
     * <p>
     * If present, we will automatically ignore the {@link Eq#naming()} value.
     * <p>
     * If not, we will automatically infer it through the field name and {@link Eq#naming()} strategy.
     *
     * @return the alias of the field in the database
     */
    String alias() default "";

    NamingEnum naming() default NamingEnum.SNAKE_CASE;
}
