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
package io.github.photowey.mybatisplus.navigator.core.enums;

/**
 * {@code DatetimePattern}
 *
 * @author photowey
 * @version 3.5.5.1.0
 * @since 2024/04/24
 */
public enum DatetimePattern {

    yyyy_MM_dd_HH_mm_ss_SSS("yyyy-MM-dd HH:mm:ss.SSS"),
    yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
    yyyy_MM_dd("yyyy-MM-dd"),
    HH_mm_ss("HH:mm:ss"),

    yyyyMMdd("yyyyMMdd"),
    yyyyMM("yyyyMM"),
    yyMM("yyMM"),
    yyMMdd("yyMMdd"),
    MM_dd("MM-dd"),
    MMdd("MMdd"),

    yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"),
    yyyyMMddHHmmss("yyyyMMddHHmmss"),
    HHmmss("HHmmss"),

    yyyy_MM_dd_T_HH_mm_ss_SSS_Z("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    yyyy_MM_dd_T_HH_mm_ss_Z("yyyy-MM-dd'T'HH:mm:ss.'Z'"),

    ;

    private final String pattern;

    DatetimePattern(String pattern) {
        this.pattern = pattern;
    }

    public String pattern() {
        return this.pattern;
    }
}
