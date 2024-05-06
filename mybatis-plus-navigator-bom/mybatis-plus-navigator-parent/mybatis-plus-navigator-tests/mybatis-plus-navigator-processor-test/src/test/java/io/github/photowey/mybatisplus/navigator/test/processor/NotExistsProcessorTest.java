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
package io.github.photowey.mybatisplus.navigator.test.processor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.photowey.mybatisplus.navigator.test.App;
import io.github.photowey.mybatisplus.navigator.test.LocalTest;
import io.github.photowey.mybatisplus.navigator.test.core.domain.entity.Employee;
import io.github.photowey.mybatisplus.navigator.test.core.domain.entity.Organization;
import io.github.photowey.mybatisplus.navigator.test.core.query.EmployeeMultiNotExistQuery;
import io.github.photowey.mybatisplus.navigator.test.core.query.EmployeeNotExistQuery;
import io.github.photowey.mybatisplus.navigator.test.core.query.OrganizationNotExistQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * {@code NotExistsProcessorTest}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/05/06
 */
@SpringBootTest(classes = App.class)
class NotExistsProcessorTest extends LocalTest {

    void holdOn() {
        App.holdOn();
    }

    @Test
    void testNotExists() {
        OrganizationNotExistQuery query = OrganizationNotExistQuery.builder()
                .build();

        QueryWrapper<Organization> wrapper = query.tryVisitQueryWrapper();
        String targetSql = wrapper.getTargetSql();
        Assertions.assertEquals("(NOT EXISTS (SELECT id FROM organization WHERE organization_no = 89757))", targetSql);
    }

    @Test
    void testNotExists_has_value() {
        EmployeeNotExistQuery query = EmployeeNotExistQuery.builder()
                .organizationIdExists(89757L)
                .build();

        QueryWrapper<Employee> wrapper = query.tryVisitQueryWrapper();
        String targetSql = wrapper.getTargetSql();
        Assertions.assertEquals("(NOT EXISTS (SELECT id FROM employee WHERE organization_id = ?))", targetSql);
    }

    @Test
    void testNotExists_has_multi_value() {
        EmployeeMultiNotExistQuery query = EmployeeMultiNotExistQuery.builder()
                .employeeExists(Arrays.asList("89757", 1))
                .build();

        QueryWrapper<Employee> wrapper = query.tryVisitQueryWrapper();
        String targetSql = wrapper.getTargetSql();
        Assertions.assertEquals("(NOT EXISTS (SELECT id FROM employee WHERE employee_no = ? AND status = ?))", targetSql);
    }
}