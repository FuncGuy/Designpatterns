/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package interpreter.exercise1;

import org.junit.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class ExpressionTest {
    @Test
    public void testExpression1() {
        assertEquals("15", ExpressionFactory.getExpression1().interpret());
    }

    @Test
    public void testExpression2() {
        assertEquals("463/120", ExpressionFactory.getExpression2().interpret());
    }

    @Test
    public void testExpression3() {
        assertEquals("49/240", ExpressionFactory.getExpression3().interpret());
    }

    @Test
    public void testExpression4() {
        assertEquals("-1/3", ExpressionFactory.getExpression4().interpret());
    }

    @Test
    public void testBasicSum() {
        assertEquals("63/64",
            new Sum(
                new Value("1/2"),
                new Value("1/4"),
                new Value("1/8"),
                new Value("1/16"),
                new Value("1/32"),
                new Value("1/64")
            ).toString()
        );
    }

    @Test
    public void testComplicatedSum() {
        // 1/3 + 2/3 / 1/2 * 3/4 + 1/2 - 5/6
        assertEquals("1",
            new Sum(
                new Value("1/3"),
                new Product(
                    new Value("2/3"),
                    new Value("2/1"),
                    new Value("3/4")
                ),
                new Value("1/2"),
                new Value("-5/6")
            ).toString());
    }

    @Test
    public void matchValueRegex() {
        assertTrue(Value.VALUE_FILTER.test("-1/2"));
        assertTrue(Value.VALUE_FILTER.test("1/-2"));
        assertTrue(Value.VALUE_FILTER.test("-1/-2"));
        assertTrue(Value.VALUE_FILTER.test("1/2"));
        assertTrue(Value.VALUE_FILTER.test("1"));
        assertTrue(Value.VALUE_FILTER.test("-1"));
        assertFalse(Value.VALUE_FILTER.test("/2"));
        assertFalse(Value.VALUE_FILTER.test("  1/2  "));
        assertFalse(Value.VALUE_FILTER.test("  a/2  "));
        assertFalse(Value.VALUE_FILTER.test("  a/ 2  "));
        assertFalse(Value.VALUE_FILTER.test("1 / 2"));
    }

    @Test
    public void testFacade() {
        assertEquals("6", Expressions.sum("1", "6/3", "90/30").interpret());
        assertEquals("2", Expressions.product("1/2", "2/1", "4/5", "5/2").interpret());
        assertEquals("0", Expressions.sum().interpret());
        assertEquals("1", Expressions.product().interpret());
        assertEquals("-10", Expressions.negate(new Value("10")).interpret());
        assertEquals("10", Expressions.negate(new Value("-10")).interpret());
    }

    @Test
    public void testFacadeStructure() throws NoSuchMethodException {
        assertTrue("Facade should have a private constructor",
            Modifier.isPrivate(Expressions.class.getDeclaredConstructor().getModifiers()));
    }
}
