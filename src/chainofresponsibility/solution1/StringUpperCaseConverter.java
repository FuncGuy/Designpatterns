/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package chainofresponsibility.solution1;

public class StringUpperCaseConverter extends Converter {
    public StringUpperCaseConverter(Converter next) {
        super(next);
    }

    public Object handle(Object o) {
        if (o instanceof String) {
            o = ((String) o).toUpperCase();
        }
        return super.handle(o);
    }
}
