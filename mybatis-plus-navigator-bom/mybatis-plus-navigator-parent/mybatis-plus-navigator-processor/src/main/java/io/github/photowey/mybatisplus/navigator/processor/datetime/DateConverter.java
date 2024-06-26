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
package io.github.photowey.mybatisplus.navigator.processor.datetime;

import io.github.photowey.mybatisplus.navigator.core.util.DatetimeUtils;
import io.github.photowey.mybatisplus.navigator.processor.annotation.component.converter.DatetimeConverter;

import java.util.Date;

/**
 * {@code DateConverter}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/02
 */
@DatetimeConverter(value = Date.class)
public class DateConverter implements TimeConverter<Date> {

    @Override
    public boolean supports(Class<?> clazz) {
        return Date.class.isAssignableFrom(clazz);
    }

    @Override
    public Date handle(Long timestamp) {
        return DatetimeUtils.epochMilliToDate(timestamp);
    }
}