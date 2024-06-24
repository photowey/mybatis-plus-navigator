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
package io.github.photowey.mybatisplus.navigator.repository.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import io.github.photowey.mybatisplus.navigator.repository.property.NavigatorProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code MybatisPlusRepositoryAutoConfigure}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/06/24
 */
@Configuration
@EnableConfigurationProperties(NavigatorProperties.class)
public class MybatisPlusNavigatorRepositoryAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
    public MybatisPlusInterceptor mybatisPlusInterceptor(NavigatorProperties props) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(props.getDbType());
        paginationInnerInterceptor.setOverflow(props.isOverflow());
        paginationInnerInterceptor.setOptimizeJoin(props.isOptimizeJoin());
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        return interceptor;
    }

}