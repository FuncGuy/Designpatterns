/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package builder.exercise2;

import org.junit.*;

import java.time.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class CalendarTest {
    @Test
    public void testOnlyDate() {
        ZonedDateTime then = ZonedDateTime.of(
            1994, 2, 19, 12, 15, 0, 0,
            ZoneId.of("Africa/Johannesburg")
        );
        assertEquals(then.toInstant().toEpochMilli(),
            new CalendarGenerator().get().getTimeInMillis());
    }
}
