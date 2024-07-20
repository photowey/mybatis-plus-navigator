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
package io.github.photowey.mybatisplus.navigator.meta.filler;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import io.github.photowey.mybatisplus.navigator.core.domain.entity.Root;
import org.apache.ibatis.reflection.MetaObject;

/**
 * {@code DefaultMybatisPlusNavigatorMetaFiller}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/07/20
 */
public class DefaultMybatisPlusNavigatorMetaFiller extends AbstractMetaPropertiesFillerAdaptor {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (ObjectUtils.isNotEmpty(metaObject) && metaObject.getOriginalObject() instanceof Root) {
            Root root = (Root) metaObject.getOriginalObject();
            super.handleInsertFill(root);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (ObjectUtils.isNotEmpty(metaObject) && metaObject.getOriginalObject() instanceof Root) {
            Root root = (Root) metaObject.getOriginalObject();
            super.handleUpdateFill(root);
        }
    }
}