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
package io.github.photowey.mybatisplus.navigator.core.domain.operator;

import java.io.Serializable;
import java.util.Objects;

/**
 * {@code Operator}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/07/21
 */
public class Operator implements Serializable {

    private static final long serialVersionUID = 2531501406388824427L;

    protected Long operatorId;
    protected String operatorName;

    // ------------------------------------------------------------

    public static OperatorBuilder builder() {
        return new OperatorBuilder();
    }

    public Long getOperatorId() {
        return this.operatorId;
    }

    public String getOperatorName() {
        return this.operatorName;
    }

    public void setOperatorId(final Long operatorId) {
        this.operatorId = operatorId;
    }

    public void setOperatorName(final String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operator operator = (Operator) o;
        return Objects.equals(operatorId, operator.operatorId) && Objects.equals(operatorName, operator.operatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatorId, operatorName);
    }

    @Override
    public String toString() {
        return "Operator(operatorId=" + this.getOperatorId() + ", operatorName=" + this.getOperatorName() + ")";
    }

    public Operator() {
    }

    public Operator(final Long operatorId, final String operatorName) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
    }

    public static class OperatorBuilder {
        private Long operatorId;
        private String operatorName;

        OperatorBuilder() {}

        public OperatorBuilder operatorId(final Long operatorId) {
            this.operatorId = operatorId;
            return this;
        }

        public OperatorBuilder operatorName(final String operatorName) {
            this.operatorName = operatorName;
            return this;
        }

        public Operator build() {
            return new Operator(this.operatorId, this.operatorName);
        }

        @Override
        public String toString() {
            return "Operator.OperatorBuilder(operatorId=" + this.operatorId + ", operatorName=" + this.operatorName + ")";
        }
    }
}
