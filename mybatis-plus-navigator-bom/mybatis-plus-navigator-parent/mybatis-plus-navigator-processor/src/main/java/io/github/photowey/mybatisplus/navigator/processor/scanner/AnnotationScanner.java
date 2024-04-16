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
package io.github.photowey.mybatisplus.navigator.processor.scanner;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import io.github.photowey.mybatisplus.navigator.core.thrower.AssertionErrorThrower;
import io.github.photowey.mybatisplus.navigator.processor.annotation.component.criteria.CriteriaProcessor;
import io.github.photowey.mybatisplus.navigator.processor.constant.ProcessorConstants;
import io.github.photowey.mybatisplus.navigator.processor.criteria.CriteriaAnnotationProcessor;
import io.github.photowey.mybatisplus.navigator.processor.datetime.TimeConverter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code AnnotationScanner}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/16
 */
public final class AnnotationScanner {

    private AnnotationScanner() {
        AssertionErrorThrower.throwz(AnnotationScanner.class);
    }

    // ----------------------------------------------------------------

    public static Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> tryProcessorScan(String... basePackages) throws IOException {
        Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> cacheMap = new HashMap<>(32);
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
        for (String basePackage : basePackages) {
            String path = resolvePackage(basePackage);
            Resource[] resources = patternResolver.getResources(path);
            for (Resource resource : resources) {
                tryReadProcessor(resource, readerFactory, cacheMap);
            }
        }

        return cacheMap;
    }

    // ----------------------------------------------------------------

    public static Map<Class<?>, TimeConverter<?>> tryConverterScan(String... basePackages) throws IOException {
        Map<Class<?>, TimeConverter<?>> cacheMap = new HashMap<>(16);
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
        for (String basePackage : basePackages) {
            String path = resolvePackage(basePackage);
            Resource[] resources = patternResolver.getResources(path);
            for (Resource resource : resources) {
                tryReadConverter(resource, readerFactory, cacheMap);
            }
        }

        return cacheMap;
    }

    // ----------------------------------------------------------------

    private static void tryReadProcessor(
            Resource resource,
            CachingMetadataReaderFactory readerFactory,
            Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> cacheMap) {
        try {
            MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
            ClassMetadata metadata = metadataReader.getClassMetadata();
            if (metadata.isInterface() || metadata.isAbstract()) {
                return;
            }
            String className = metadata.getClassName();
            Class<?> clazz = ClassUtils.forName(className, AnnotationScanner.class.getClassLoader());
            if (clazz.isAnnotationPresent(CriteriaProcessor.class)) {
                CriteriaProcessor annotation = clazz.getAnnotation(CriteriaProcessor.class);
                CriteriaAnnotationProcessor processor = (CriteriaAnnotationProcessor) clazz.getDeclaredConstructor().newInstance();
                Class<? extends Annotation> criteria = annotation.criteria();
                cacheMap.put(criteria, processor);
            }
        } catch (Exception ignored) {
        }
    }

    private static void tryReadConverter(Resource resource, CachingMetadataReaderFactory readerFactory, Map<Class<?>, TimeConverter<?>> cacheMap) {
        try {
            MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            if (classMetadata.isInterface() || classMetadata.isAbstract()) {
                return;
            }
            String className = classMetadata.getClassName();
            Class<?> clazz = ClassUtils.forName(className, AnnotationScanner.class.getClassLoader());
            if (TimeConverter.class.isAssignableFrom(clazz)) {
                TimeConverter converter = (TimeConverter) clazz.getDeclaredConstructor().newInstance();
                cacheMap.put(clazz, converter);
            }
        } catch (Exception ignored) {
        }
    }

    // ----------------------------------------------------------------

    private static String convertClassNameToResourcePath(String className) {
        Assert.notNull(className, "Class name must not be null");
        return className.replace(ProcessorConstants.PACKAGE_SEPARATOR, ProcessorConstants.PATH_SEPARATOR);
    }

    private static String resolvePackage(String basePackage) {
        return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + convertClassNameToResourcePath(basePackage)
                + ProcessorConstants.PATH_SEPARATOR
                + ProcessorConstants.DEFAULT_RESOURCE_PATTERN;
    }
}
