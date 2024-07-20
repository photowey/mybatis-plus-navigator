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
package io.github.photowey.mybatisplus.navigator.meta.selector;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import io.github.photowey.mybatisplus.navigator.core.exception.MultiBeanException;
import io.github.photowey.mybatisplus.navigator.meta.filler.DefaultMybatisPlusNavigatorMetaFiller;
import io.github.photowey.mybatisplus.navigator.meta.filler.MybatisPlusNavigatorMetaFiller;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * {@code MybatisPlusNavigatorMetaFillerSelector}
 *
 * @author photowey
 * @version 3.5.5.0.0
 * @since 2024/07/20
 */
public class MybatisPlusNavigatorMetaFillerSelector implements /*ImportSelector*/ DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> fillers = SpringFactoriesLoader.loadFactoryNames(MybatisPlusNavigatorMetaFiller.class, ClassUtils.getDefaultClassLoader());
        if (CollectionUtils.isEmpty(fillers)) {
            fillers.add(DefaultMybatisPlusNavigatorMetaFiller.class.getName());
        }
        if (fillers.size() > 1) {
            throw new MultiBeanException("navigator: MybatisPlusNavigatorMetaFiller subclass must have exactly one");
        }

        return StringUtils.toStringArray(fillers);
    }

    @Override
    public Class<? extends Group> getImportGroup() {
        return MetaFillerDeferredImportSelectorGroup.class;
    }

    private static class MetaFillerDeferredImportSelectorGroup implements DeferredImportSelector.Group {

        private List<Entry> candidates = new ArrayList<>();

        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            String[] selectImports = selector.selectImports(metadata);
            Stream.of(selectImports).forEach((candidateClassName) -> {
                this.candidates.add(new Entry(metadata, candidateClassName));
            });
        }

        @Override
        public Iterable<Entry> selectImports() {
            return this.candidates;
        }
    }
}
