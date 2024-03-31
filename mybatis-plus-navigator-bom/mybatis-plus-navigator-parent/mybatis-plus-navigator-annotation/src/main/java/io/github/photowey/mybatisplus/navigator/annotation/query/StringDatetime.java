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
 * # Examples:
 * <pre>
 * public class HelloQuery implements Serializable {
 *
 *     // created_at >= ${createdAt}
 *    {@literal @}StringDatetime(alias = "created_at", compare = OperatorEnum.GE, clazz = LocalDateTime.class)
 *     private String createdAt;
 *
 *     // update_time <= ${updateTime}
 *    {@literal @}StringDatetime(compare = OperatorEnum.LE, clazz = LocalDateTime.class, naming = NamingEnum.SNAKE_CASE)
 *     private String updateTime;
 *
 *     // refresh_date > ${refreshDate}
 *    {@literal @}StringDatetime(pattern = DatetimeConstants.yyyy_MM_dd, compare = OperatorEnum.GT, clazz = LocalDate.class, naming = NamingEnum.SNAKE_CASE)
 *     private String refreshDate;
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
public @interface StringDatetime {

    String alias() default "";

    String pattern() default "yyyy-MM-dd HH:mm:ss";

    Operator compare() default Operator.EQ;

    Class<?> clazz() default LocalDateTime.class;

    NamingStrategy naming() default NamingStrategy.SNAKE_CASE;
}
