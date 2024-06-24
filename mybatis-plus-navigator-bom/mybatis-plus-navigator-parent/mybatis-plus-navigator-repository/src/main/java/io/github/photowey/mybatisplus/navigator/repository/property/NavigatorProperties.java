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
package io.github.photowey.mybatisplus.navigator.repository.property;

import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@code NavigatorProperties}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/06/24
 */
@ConfigurationProperties(prefix = "mybatisplus.navigator")
public class NavigatorProperties {

    private DbType dbType = DbType.MYSQL;
    private boolean overflow = true;
    private boolean useDeprecatedExecutor = false;

    protected boolean optimizeJoin = true;

    // ----------------------------------------------------------------

    public DbType getDbType() {
        return dbType;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public boolean isUseDeprecatedExecutor() {
        return useDeprecatedExecutor;
    }

    public boolean isOptimizeJoin() {
        return optimizeJoin;
    }

    // ----------------------------------------------------------------


    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

    public void setUseDeprecatedExecutor(boolean useDeprecatedExecutor) {
        this.useDeprecatedExecutor = useDeprecatedExecutor;
    }

    public void setOptimizeJoin(boolean optimizeJoin) {
        this.optimizeJoin = optimizeJoin;
    }
}
