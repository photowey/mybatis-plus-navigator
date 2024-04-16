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
package io.github.photowey.mybatisplus.navigator.processor.constant;

import io.github.photowey.mybatisplus.navigator.processor.criteria.Processor;
import io.github.photowey.mybatisplus.navigator.processor.datetime.Time;

/**
 * {@code ProcessorConstants}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/16
 */
public interface ProcessorConstants {

    String ANNOTATION_PROCESSOR_SCAN_BASE_PACKAGE = Processor.class.getPackage().getName();
    String TIME_PROCESSOR_SCAN_BASE_PACKAGE = Time.class.getPackage().getName();

    String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    char PACKAGE_SEPARATOR = '.';
    char PATH_SEPARATOR = '/';

}
