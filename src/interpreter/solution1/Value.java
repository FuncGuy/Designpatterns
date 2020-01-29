/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package interpreter.solution1;

import maths.*;

import java.util.function.*;

//DON'T CHANGE
public class Value implements Expression {
    public static final Predicate<String> VALUE_FILTER =
        value -> value.matches("^-?[0-9]+(/-?[0-9]+)?");
    private final Fraction value;

    public Value(String value) {
        if (!VALUE_FILTER.test(value))
            throw new IllegalArgumentException("Invalid value in \"" + value + "\"");
        this.value = new Fraction(value);
    }

    public String interpret() {
        return value.toString();
    }

    public String toString() {
        return interpret();
    }
}
