/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package chainofresponsibility.exercise1;

public abstract class Converter {
    // you will need a handle to the next converter

    public Object handle(Object o) {
        // if the next converter is non-null, we call its handle method
        throw new UnsupportedOperationException("todo");
    }
}
