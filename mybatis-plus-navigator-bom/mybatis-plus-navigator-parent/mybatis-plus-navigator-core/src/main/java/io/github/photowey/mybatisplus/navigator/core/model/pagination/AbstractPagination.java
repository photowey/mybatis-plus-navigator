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
package io.github.photowey.mybatisplus.navigator.core.model.pagination;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code AbstractPagination}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/03/31
 */
public abstract class AbstractPagination implements Pagination, Serializable {

    protected Long current;
    protected Long size;

    private Long total;
    /**
     * Database columns
     * final or NOT ?
     * <p>
     * -&gt; SELECT columns...
     */
    private final Set<String> columns = new HashSet<>();

    // ----------------------------------------------------------------

    public static <T> Page<T> populateDefaultPage() {
        return new Page<>(Pagination.DEFAULT_CURRENT, Pagination.DEFAULT_SIZE);
    }

    // ----------------------------------------------------------------

    public void tryThresholdSizeEnabled() {
        this.setSize(THRESHOLD_SIZE);
    }

    // ----------------------------------------------------------------

    public Long getTotal() {
        return null != this.total ? this.total : 0L;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    // ----------------------------------------------------------------

    public Long total() {
        return this.getTotal();
    }

    public void total(Long total) {
        this.total = total;
    }

    // ----------------------------------------------------------------

    public Long getLimit() {
        return this.getSize();
    }

    public Long getOffset() {
        long current = this.getCurrent();
        if (current <= 1L) {
            return 0L;
        }
        return Math.max((current - 1) * getSize(), 0L);
    }

    // ----------------------------------------------------------------

    @Override
    public Long getCurrent() {
        return this.current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    @Override
    public Long getSize() {
        return this.size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Set<String> getColumns() {
        return this.columns;
    }

    // ----------------------------------------------------------------

    public void selectOne() {
        this.selectPage(1L, 1L);
    }

    public void selectPage(long current, long size) {
        this.setCurrent(current);
        this.setSize(size);
    }

    public <Q extends AbstractPagination> void selectPage(Q pagination) {
        this.selectPage(pagination.getCurrent(), pagination.getSize());
    }

    // ----------------------------------------------------------------

    public void tryFilterProperties(String... properties) {
        this.tryFilterProperties(true, properties);
    }

    public void tryFilterProperties(boolean filter, String... properties) {
        List<String> columns = Stream.of(properties).map(v -> this.toColumn(filter, v)).collect(Collectors.toList());
        this.columns.addAll(columns);
    }

    public void tryFilterProperties(Function<String, String> fx, String... properties) {
        List<String> columns = Stream.of(properties).map(fx).collect(Collectors.toList());
        this.columns.addAll(columns);
    }

    // ----------------------------------------------------------------

    private String toColumn(boolean filter, String property) {
        if (filter) {
            return this.toUnderlineColumn(property);
        }

        return property;
    }

    private String toUnderlineColumn(String property) {
        return StringUtils.camelToUnderline(property);
    }
}
