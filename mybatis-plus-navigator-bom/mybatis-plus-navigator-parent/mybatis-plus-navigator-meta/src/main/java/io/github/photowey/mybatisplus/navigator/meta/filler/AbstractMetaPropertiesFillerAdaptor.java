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
import io.github.photowey.mybatisplus.navigator.core.domain.entity.CreatorEntity;
import io.github.photowey.mybatisplus.navigator.core.domain.entity.Root;
import io.github.photowey.mybatisplus.navigator.core.domain.entity.RootAtEntity;
import io.github.photowey.mybatisplus.navigator.core.domain.entity.RootEntity;
import io.github.photowey.mybatisplus.navigator.core.domain.operator.Operator;
import io.github.photowey.mybatisplus.navigator.meta.operator.OperatorTranslator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@code AbstractMetaPropertiesFillerAdaptor}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/07/21
 */
public abstract class AbstractMetaPropertiesFillerAdaptor implements MybatisPlusNavigatorMetaFiller {

    protected ListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

    protected OperatorTranslator tryAcquireOperatorTranslator() {
        try {
            Map<String, OperatorTranslator> beans = this.beanFactory.getBeansOfType(OperatorTranslator.class);
            if (ObjectUtils.isNotEmpty(beans)) {
                List<OperatorTranslator> translators = new ArrayList<>(beans.values());
                AnnotationAwareOrderComparator.sort(translators);

                return translators.get(0);
            }
        } catch (Exception ignored) {}

        return this.notFound();
    }

    // ----------------------------------------------------------------

    protected void handleInsertFill(Root root) {
        this.handleInsertRootFill(root);
        this.handleInsertRootEntityFill(root);
        this.handleInsertRootAtEntityFill(root);
        this.handleInsertCreatorFill(root);
    }

    protected void handleUpdateFill(Root root) {
        this.handleUpdateRootEntityFill(root);
        this.handleUpdateRootAtEntityFill(root);
        this.handleUpdateCreatorFill(root);
    }

    // ----------------------------------------------------------------

    protected void handleInsertRootFill(Root root) {
        if (Objects.isNull(root.getDeleted())) {
            root.setDeleted(0);
        }
    }

    protected void handleInsertRootEntityFill(Root root) {
        if (root instanceof RootEntity) {
            RootEntity entity = (RootEntity) root;
            LocalDateTime now = LocalDateTime.now();
            if (Objects.isNull((entity.getCreateTime()))) {
                entity.setCreateTime(now);
            }
            if (Objects.isNull(entity.getUpdateTime())) {
                entity.setUpdateTime(now);
            }
        }
    }

    protected void handleInsertRootAtEntityFill(Root root) {
        if (root instanceof RootAtEntity) {
            RootAtEntity entity = (RootAtEntity) root;
            LocalDateTime now = LocalDateTime.now();
            if (Objects.isNull((entity.getCreatedAt()))) {
                entity.setCreatedAt(now);
            }
            if (Objects.isNull(entity.getUpdatedAt())) {
                entity.setUpdatedAt(now);
            }
        }
    }

    protected void handleInsertCreatorFill(Root root) {
        if (root instanceof CreatorEntity) {
            CreatorEntity entity = (CreatorEntity) root;

            OperatorTranslator translator = this.tryAcquireOperatorTranslator();
            Operator operator = translator.tryAcquireOperator();
            if (ObjectUtils.isNotEmpty(operator) && ObjectUtils.isNotEmpty(operator.getOperatorId())) {
                Long operatorId = operator.getOperatorId();
                if (ObjectUtils.isEmpty(entity.getCreatedBy())) {
                    entity.setCreatedBy(operatorId);
                }
                if (ObjectUtils.isEmpty(entity.getUpdatedBy())) {
                    entity.setUpdatedBy(operatorId);
                }
            }
        }
    }

    // ----------------------------------------------------------------

    protected void handleUpdateRootEntityFill(Root root) {
        if (root instanceof RootEntity) {
            RootEntity entity = (RootEntity) root;
            LocalDateTime now = LocalDateTime.now();
            if (Objects.isNull(entity.getUpdateTime())) {
                entity.setUpdateTime(now);
            }
        }
    }

    protected void handleUpdateRootAtEntityFill(Root root) {
        if (root instanceof RootAtEntity) {
            RootAtEntity entity = (RootAtEntity) root;
            LocalDateTime now = LocalDateTime.now();
            if (Objects.isNull(entity.getUpdatedAt())) {
                entity.setUpdatedAt(now);
            }
        }
    }

    protected void handleUpdateCreatorFill(Root root) {
        if (root instanceof CreatorEntity) {
            CreatorEntity entity = (CreatorEntity) root;

            OperatorTranslator translator = this.tryAcquireOperatorTranslator();
            Operator operator = translator.tryAcquireOperator();
            if (ObjectUtils.isNotEmpty(operator) && ObjectUtils.isNotEmpty(operator.getOperatorId())) {
                Long operatorId = operator.getOperatorId();
                if (ObjectUtils.isEmpty(entity.getUpdatedBy())) {
                    entity.setUpdatedBy(operatorId);
                }
            }
        }
    }

    // ----------------------------------------------------------------

    private <T> T notFound() {
        throw new NullPointerException("navigator: Operator's translator: [io.github.photowey.mybatisplus.navigator.meta.operator.OperatorTranslator] subclass not found(404)");
    }
}