/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package chainofresponsibility.solution1;

public class StringTrimmerConverter extends Converter {
    public StringTrimmerConverter(Converter next) {
        super(next);
    }

    public Object handle(Object o) {
        // if the object is a String, we trim the whitespace
        if (o instanceof String) {
            o = ((String) o).trim();
        }
        return super.handle(o);
    }
}
