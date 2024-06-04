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
package io.github.photowey.mybatisplus.navigator.core.domain.sorting;

import io.github.photowey.mybatisplus.navigator.core.enums.SortingOrder;

import java.io.Serializable;
import java.util.Objects;

/**
 * {@code SortingTerm}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/16
 */
public class SortingTerm implements Serializable {

    private static final long serialVersionUID = -7493258064912684126L;

    private String column;
    private SortingOrder order;

    public SortingTerm() {
    }

    public SortingTerm(String column, SortingOrder order) {
        this.column = column;
        this.order = order;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public SortingOrder getOrder() {
        return order;
    }

    public void setOrder(SortingOrder order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortingTerm that = (SortingTerm) o;
        return Objects.equals(column, that.column) && order == that.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, order);
    }
}