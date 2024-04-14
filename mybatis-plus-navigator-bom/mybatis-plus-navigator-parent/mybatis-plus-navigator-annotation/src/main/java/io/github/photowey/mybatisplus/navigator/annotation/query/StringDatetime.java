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
import io.github.photowey.mybatisplus.navigator.core.enums.Operator;

import java.lang.annotation.*;
import java.time.LocalDateTime;

/**
 * {@code StringDatetime}
 * <p>
 * Examples:
 * <pre>
 * public class HelloQuery implements Serializable {
 *
 *     // created_at &gt;= ${createdAt}
 *    {@literal @}StringDatetime(alias = "created_at", compare = OperatorEnum.GE, clazz = LocalDateTime.class)
 *     private String createdAt;
 *
 *     // update_time &lt;= ${updateTime}
 *    {@literal @}StringDatetime(compare = OperatorEnum.LE, clazz = LocalDateTime.class, naming = NamingEnum.SNAKE_CASE)
 *     private String updateTime;
 *
 *     // refresh_date &gt; ${refreshDate}
 *    {@literal @}StringDatetime(pattern = DatetimeConstants.yyyy_MM_dd, compare = OperatorEnum.GT, clazz = LocalDate.class, naming = NamingEnum.SNAKE_CASE)
 *     private String refreshDate;
 * }
 * </pre>
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/03/20
 */
@Documented
@CriteriaQuery
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringDatetime {

    /**
     * The alias
     *
     * @return the alias of {@code Database} column.
     */
    String alias() default "";

    /**
     * The {@code String} pattern of date-time.
     *
     * @return the {@code String} pattern of date-time Object.
     */
    String pattern() default "yyyy-MM-dd HH:mm:ss";

    /**
     * The operator of compare.
     *
     * @return the operator of compare.
     */
    Operator compare() default Operator.EQ;

    /**
     * The {@link Class} of date-time Object.
     *
     * @return the {@link Class} of date-time Object.
     */
    Class<?> clazz() default LocalDateTime.class;

    /**
     * The {@code Database} column name strategy.
     *
     * @return the {@code Database} column name strategy.
     */
    NamingStrategy naming() default NamingStrategy.SNAKE_CASE;
}
