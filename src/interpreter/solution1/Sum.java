/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package interpreter.solution1;

import maths.*;

import java.util.stream.*;

//DON'T CHANGE
public class Sum implements Expression {
    private final Expression[] expressions;

    public Sum(Expression... expressions) {
        this.expressions = expressions;
    }

    public String interpret() {
        return Stream.of(expressions)
            .map(Object::toString)
            .map(Fraction::new)
            .reduce(Fraction.ZERO, Fraction::add)
            .toString();
    }

    public String toString() {
        return interpret();
    }
}
