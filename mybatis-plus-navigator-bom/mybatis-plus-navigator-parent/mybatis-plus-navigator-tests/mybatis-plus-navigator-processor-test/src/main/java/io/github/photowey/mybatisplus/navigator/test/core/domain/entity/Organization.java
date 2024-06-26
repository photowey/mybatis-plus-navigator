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
package io.github.photowey.mybatisplus.navigator.test.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * {@code Organization}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("Organization")
@TableName(value = "organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = -7400199381089063967L;

    private Long id;

    private String organizationNo;
    private String organizationName;

    private Integer sorting;
    private Integer status;
    private String remark;

    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Integer deleted;
}