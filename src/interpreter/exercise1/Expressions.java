/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package interpreter.exercise1;

/**
 * Facade for Expression elements, to make the framework easier to use.
 */
public class Expressions {
    public static Expression sum(String... numbers) {
        return new Value("TODO");
    }

    public static Expression product(String... numbers) {
        return new Value("TODO");
    }

    public static Expression negate(Expression exp) {
        return new Value("TODO");
    }
}
