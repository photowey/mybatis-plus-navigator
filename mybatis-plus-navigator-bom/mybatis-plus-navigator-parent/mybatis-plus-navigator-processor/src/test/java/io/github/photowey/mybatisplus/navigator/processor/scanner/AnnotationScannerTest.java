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

import io.github.photowey.mybatisplus.navigator.processor.constant.ProcessorConstants;
import io.github.photowey.mybatisplus.navigator.processor.criteria.CriteriaAnnotationProcessor;
import io.github.photowey.mybatisplus.navigator.processor.datetime.TimeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * {@code AnnotationScannerTest}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/17
 */
class AnnotationScannerTest {

    @Test
    void testProcessorScan() throws IOException {
        Map<Class<? extends Annotation>, CriteriaAnnotationProcessor> processorMap =
                AnnotationScanner.tryProcessorScan(ProcessorConstants.ANNOTATION_PROCESSOR_SCAN_BASE_PACKAGE);

        Assertions.assertEquals(25, processorMap.size());
    }

    @Test
    void testConverterScan() throws IOException {
        Map<Class<?>, TimeConverter<?>> converterMap =
                AnnotationScanner.tryConverterScan(ProcessorConstants.TIME_PROCESSOR_SCAN_BASE_PACKAGE);

        Assertions.assertEquals(6, converterMap.size());
    }
}