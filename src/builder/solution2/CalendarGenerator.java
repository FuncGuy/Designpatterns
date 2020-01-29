/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package builder.solution2;

import java.util.*;
import java.util.function.*;

public class CalendarGenerator implements Supplier<Calendar> {
    public Calendar get() {
        return new Calendar.Builder()
            .setCalendarType("gregory")
            .setTimeZone(TimeZone.getTimeZone("Africa/Johannesburg"))
            .setDate(1994, Calendar.FEBRUARY, 19)
            .setTimeOfDay(12, 15, 0)
            .build();
    }
}
