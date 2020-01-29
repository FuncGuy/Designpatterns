/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package chainofresponsibility.solution1;

public abstract class Converter {
    // you will need a handle to the next converter
    private final Converter next;

    protected Converter(Converter next) {
        this.next = next;
    }

    public Object handle(Object o) {
        if (next != null) {
            return next.handle(o);
        }
        return o;
    }
}
