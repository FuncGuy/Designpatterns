/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package builder.exercise2;

import java.util.*;
import java.util.function.*;

public class CalendarGenerator implements Supplier<Calendar> {
    {
        if (true)
            throw new UnsupportedOperationException("TODO");
    }

    public Calendar get() {
        Calendar cal = new GregorianCalendar(
            TimeZone.getTimeZone("Africa/Johannesburg"));
        cal.set(Calendar.YEAR, 1994);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DAY_OF_MONTH, 19);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}
