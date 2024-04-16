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
package io.github.photowey.mybatisplus.navigator.autoconfigure.config;

import io.github.photowey.mybatisplus.navigator.processor.annotation.component.converter.DatetimeConverter;
import io.github.photowey.mybatisplus.navigator.processor.datetime.TimeConverter;
import io.github.photowey.mybatisplus.navigator.processor.registry.CriteriaRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Role;

/**
 * {@code DatetimeProcessorBeanPostProcessor}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/17
 */
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class DatetimeConverterBeanPostProcessor implements BeanPostProcessor, DisposableBean {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TimeConverter) {
            DatetimeConverter datetimeConverter = bean.getClass().getAnnotation(DatetimeConverter.class);
            if (null != datetimeConverter) {
                Class<?> criteriaAnno = datetimeConverter.value();
                CriteriaRegistry.register(criteriaAnno, (TimeConverter) bean);
            }
        }

        return bean;
    }

    @Override
    public void destroy() throws Exception {
        CriteriaRegistry.destroy();
    }
}
