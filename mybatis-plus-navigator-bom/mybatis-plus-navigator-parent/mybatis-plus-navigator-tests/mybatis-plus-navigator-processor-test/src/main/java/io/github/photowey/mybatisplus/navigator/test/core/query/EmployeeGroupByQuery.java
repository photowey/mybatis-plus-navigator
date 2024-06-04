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
package io.github.photowey.mybatisplus.navigator.test.core.query;

import io.github.photowey.mybatisplus.navigator.annotation.query.Eq;
import io.github.photowey.mybatisplus.navigator.annotation.query.GroupBy;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;
import io.github.photowey.mybatisplus.navigator.test.core.domain.entity.Employee;
import lombok.*;

import java.io.Serializable;

/**
 * {@code EmployeeGroupByQuery}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/05/07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeGroupByQuery extends AbstractQuery<Employee> implements Serializable {

    private static final long serialVersionUID = 7891195884218678284L;

    @Eq
    private Long id;

    @GroupBy
    private Long organizationId;
}