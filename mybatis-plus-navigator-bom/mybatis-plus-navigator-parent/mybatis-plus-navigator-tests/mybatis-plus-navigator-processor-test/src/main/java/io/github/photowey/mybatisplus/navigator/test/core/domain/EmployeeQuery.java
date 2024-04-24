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
package io.github.photowey.mybatisplus.navigator.test.core.domain;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import io.github.photowey.mybatisplus.navigator.annotation.query.*;
import io.github.photowey.mybatisplus.navigator.core.constant.DatetimeConstants;
import io.github.photowey.mybatisplus.navigator.core.enums.NamingStrategy;
import io.github.photowey.mybatisplus.navigator.core.enums.Operator;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;
import io.github.photowey.mybatisplus.navigator.test.core.domain.entity.Employee;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * {@code EmployeeQuery}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/04/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeQuery extends AbstractQuery<Employee> implements Serializable {

    private static final long serialVersionUID = 7891195884218678284L;

    @Eq
    private Long id;

    @Ne
    private Long organizationId;

    /**
     * ORG_NAME
     */
    @Like(naming = NamingStrategy.UPPER_SNAKE_CASE)
    private String organizationName;
    @Like(alias = "employee_no", like = SqlLike.RIGHT)
    private String employeeNo;
    @NotLike(like = SqlLike.LEFT)
    private String employeeName;

    @Ge
    private Integer sorting;
    @Gt
    private Integer sortingGt;

    @Le
    private Integer status;
    @In(alias = "status")
    private List<Integer> statusIn;
    @NotIn(alias = "status")
    private List<Integer> statusNotIn;

    @Lt
    private Integer statusLt;
    private String remark;

    @Eq
    private Long createdBy;
    @Eq
    private Long updatedBy;

    @Datetime(compare = Operator.GE)
    private LocalDateTime createdAt;
    @Timestamp(compare = Operator.LT)
    private Long updatedAt;

    @StringDatetime(compare = Operator.GE)
    private String registerDatetime;
    @StringDatetime(compare = Operator.LT, pattern = DatetimeConstants.yyyy_MM_dd, clazz = LocalDate.class)
    private String registerDate;
    @StringDatetime(compare = Operator.LE, pattern = DatetimeConstants.HH_mm_ss, clazz = LocalTime.class)
    private String registerTime;
}