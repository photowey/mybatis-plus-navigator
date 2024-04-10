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
package io.github.photowey.mybatisplus.navigator.processor.criteria;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import io.github.photowey.mybatisplus.navigator.annotation.query.Like;
import io.github.photowey.mybatisplus.navigator.processor.annotation.component.criteria.CriteriaProcessor;
import io.github.photowey.mybatisplus.navigator.processor.model.query.AbstractQuery;

import java.lang.reflect.Field;

/**
 * {@code LikeProcessor}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/04/10
 */
@CriteriaProcessor(criteria = Like.class)
public class LikeProcessor<QUERY extends AbstractQuery<ENTITY>, ENTITY>
        extends AbstractCriteriaAnnotationProcessorAdaptor<Like, QUERY, QueryWrapper<ENTITY>, ENTITY> {

    @Override
    public boolean process(QueryWrapper<ENTITY> queryWrapper, Field field, QUERY query, Like annotation) {
        return this.onProcess(field, query, annotation::alias, annotation::naming, (column, value) -> {
            SqlLike like = annotation.like();
            switch (like) {
                case LEFT:
                    queryWrapper.likeLeft(column, value);
                    break;
                case RIGHT:
                    queryWrapper.likeRight(column, value);
                    break;
                case DEFAULT:
                    queryWrapper.like(column, value);
                    break;
                default:
                    break;
            }
        });
    }
}
