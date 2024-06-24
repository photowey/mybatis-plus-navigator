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
package io.github.photowey.mybatisplus.navigator.repository.ext;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.function.BiConsumer;

/**
 * {@code BatchRepositoryExt}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/06/24
 */
public interface BatchRepositoryExt<T> extends RepositoryExt<T> {

    Log log = LogFactory.getLog(BatchRepositoryExt.class);

    int DEFAULT_BATCH_SIZE = 1000;

    // ----------------------------------------------------------------

    default boolean batchInserts(Collection<T> entityList, Class<T> entityClass) {
        return this.executeBatch(entityList, entityClass, this.batchSize(), SqlMethod.INSERT_ONE);
    }

    // ----------------------------------------------------------------

    default boolean executeBatch(Collection<T> entityList, Class<T> entityClass, int batchSize, SqlMethod sqlMethod) {
        return this.executeBatch(entityList, entityClass, batchSize, (sqlSession, entity) -> {
            if (SqlMethod.INSERT_ONE.equals(sqlMethod)) {
                this.insert(entity);
            } else if (SqlMethod.UPDATE_BY_ID.equals(sqlMethod)) {
                this.updateById(entity);
            }
        });
    }

    default boolean executeBatch(Collection<T> entityList, Class<T> entityClass, int batchSize, BiConsumer<SqlSession, T> callback) {
        return SqlHelper.executeBatch(entityClass, this.log(), entityList, batchSize, callback);
    }

    // ----------------------------------------------------------------

    default Log log() {
        return log;
    }

    default int batchSize() {
        return DEFAULT_BATCH_SIZE;
    }
}
