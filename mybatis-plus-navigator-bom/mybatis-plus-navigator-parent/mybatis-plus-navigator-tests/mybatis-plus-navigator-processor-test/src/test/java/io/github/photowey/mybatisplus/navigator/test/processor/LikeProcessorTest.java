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
import io.github.photowey.mybatisplus.navigator.test.core.domain.EmployeeQuery;
import io.github.photowey.mybatisplus.navigator.test.core.domain.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@code LikeProcessorTest}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/04/22
 */
@SpringBootTest(classes = App.class)
class LikeProcessorTest extends LocalTest {

    void holdOn() {
        App.holdOn();
    }

    @Test
    void testLike() {
        EmployeeQuery query = EmployeeQuery.builder()
                .organizationName("Hello")
                .build();

        QueryWrapper<Employee> wrapper = query.tryVisitQueryWrapper();
        String targetSql = wrapper.getTargetSql();
        Assertions.assertEquals("(ORGANIZATION_NAME LIKE ?)", targetSql);
    }

    @Test
    void testLike_2() {
        EmployeeQuery query = EmployeeQuery.builder()
                .organizationName("Hello")
                .employeeNo("89757")
                .build();

        QueryWrapper<Employee> wrapper = query.tryVisitQueryWrapper();
        String targetSql = wrapper.getTargetSql();
        Assertions.assertEquals("(ORGANIZATION_NAME LIKE ? AND employee_no LIKE ?)", targetSql);
    }
}