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
package io.github.photowey.mybatisplus.navigator.core.util;

import io.github.photowey.mybatisplus.navigator.core.thrower.AssertionErrorThrower;

import java.sql.Timestamp;
import java.time.*;
import java.util.Date;

/**
 * {@code DatetimeUtils}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/04/02
 */
public final class DatetimeUtils {

    private DatetimeUtils() {
        AssertionErrorThrower.throwz(DatetimeUtils.class);
    }

    // ----------------------------------------------------------------

    public static Date epochMilliToDate(Long timestamp) {
        if (null == timestamp) {
            return null;
        }

        return new Date(timestamp);
    }

    public static Date epochMilliToDate(Timestamp timestamp) {
        if (null == timestamp) {
            return null;
        }

        return epochMilliToDate(timestamp.getTime());
    }

    public static Timestamp epochMilliToTimestamp(Long timestamp) {
        if (null == timestamp) {
            return null;
        }

        return new Timestamp(timestamp);
    }

    // ----------------------------------------------------------------

    public static LocalDateTime epochMilliToLocalDateTime(Long timestamp) {
        if (null == timestamp) {
            return null;
        }

        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static ZonedDateTime epochMilliToZonedDateTime(Long timestamp) {
        if (null == timestamp) {
            return null;
        }

        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return ZonedDateTime.ofInstant(instant, zone);
    }

    public static LocalDate epochMilliToLocalDate(Long timestamp) {
        if (null == timestamp) {
            return null;
        }

        return epochMilliToLocalDateTime(timestamp).toLocalDate();
    }

    public static LocalTime epochMilliToLocalTime(Long timestamp) {
        if (null == timestamp) {
            return null;
        }

        return epochMilliToLocalDateTime(timestamp).toLocalTime();
    }

    // ----------------------------------------------------------------

    public static Date toDate(LocalDateTime target) {
        if (null == target) {
            return null;
        }

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = target.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDate toLocalDate(LocalDateTime target) {
        if (null == target) {
            return null;
        }

        return target.toLocalDate();
    }

    public static LocalTime toLocalTime(LocalDateTime target) {
        if (null == target) {
            return null;
        }

        return target.toLocalTime();
    }
}