/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package maths;

import org.junit.*;

import static org.junit.Assert.*;

public class FractionTest {
    @Test
    public void testFraction() {
        assertEquals("1/2", new Fraction(5, 10).toString());
        assertEquals("7/6", new Fraction(1, 2).add(new Fraction(2, 3)).toString());
        assertEquals("3/8", new Fraction(1, 2).multiply(new Fraction(3, 4)).toString());
        assertEquals("1", new Fraction(1, 2).divide(new Fraction(1, 2)).toString());
        assertEquals("-1/2", new Fraction(-1, 2).toString());
        assertEquals("-1/2", new Fraction(1, -2).toString());
        assertEquals("1/2", new Fraction(-1, -2).toString());
        assertEquals("1/2", new Fraction(1, 2).toString());
        assertEquals("5", new Fraction(1, 2).multiply(new Fraction(20, 2)).toString());
    }

    @Test
    public void testFractionAdd() {
        assertEquals("0", Fraction.add());
        assertEquals("1", Fraction.add("1/2", "1 / 2", "  1 / 2", "-1/2", "1/-2", "-1/-2"));
        assertEquals("481/280", Fraction.add("1/2", "1/3", "1/4", "1/5", "1/6", "1/7", "1/8"));
    }

    @Test
    public void testFractionMultiply() {
        assertEquals("0", Fraction.multiply());
        assertEquals("1/64", Fraction.multiply("1/2", "1 / 2", "  1 / 2", "-1/2", "1/-2", "-1/-2"));
        assertEquals("1/40320", Fraction.multiply("1/2", "1/3", "1/4", "1/5", "1/6", "1/7", "1/8"));
    }
}
