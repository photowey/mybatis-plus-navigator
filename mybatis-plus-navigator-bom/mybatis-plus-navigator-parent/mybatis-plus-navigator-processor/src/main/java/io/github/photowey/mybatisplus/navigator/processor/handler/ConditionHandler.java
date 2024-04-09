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
package io.github.photowey.mybatisplus.navigator.processor.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;

/**
 * {@code ConditionHandler}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/04/02
 */
public interface ConditionHandler {

    default <T> void handleAnd(QueryWrapper<T> queryWrapper, AbstractQuery<T> query, Field field) {

    }

    default <T> void handleOr(QueryWrapper<T> queryWrapper, AbstractQuery<?> query, Field field) {

    }
}
