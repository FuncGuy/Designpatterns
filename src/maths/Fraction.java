/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */ 
package maths;

import java.util.stream.*;

public final class Fraction {
    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction TWO = new Fraction(2, 1);
    private final long numerator;
    private final long denominator;

    public Fraction(String fraction) {
        this(extractNumerator(fraction), extractDenominator(fraction));
    }

    private static long extractDenominator(String fraction) {
        String[] vals = fraction.split("/");
        if (vals.length < 1)
            throw new IllegalArgumentException("Invalid fraction in \"" + fraction + "\"");
        if (vals.length == 1) return 1;
        return Long.parseLong(vals[1].trim());

    }

    private static long extractNumerator(String fraction) {
        String[] vals = fraction.split("/");
        if (vals.length < 1)
            throw new IllegalArgumentException("Invalid fraction in \"" + fraction + "\"");
        return Long.parseLong(vals[0].trim());
    }

    public Fraction(long numerator, long denominator) {
        if (denominator == 0)
            throw new ArithmeticException("Divide by zero");
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        long lcd = Numbers.findLCD(numerator, denominator);
        this.numerator = numerator / lcd;
        this.denominator = denominator / lcd;
    }

    public static String add(String... fractions) {
        if (fractions.length == 0) return "0";
        return Stream.of(fractions)
            .map(Fraction::new)
            .reduce(ZERO, Fraction::add)
            .toString();
    }

    public static String multiply(String... fractions) {
        if (fractions.length == 0) return "0";
        return Stream.of(fractions)
            .map(Fraction::new)
            .reduce(ONE, Fraction::multiply)
            .toString();
    }

    public Fraction add(Fraction other) {
        // 1/2 + 2/3 = (1*3 + 2*2) /6
        long newNumerator = numerator * other.denominator +
            other.numerator * denominator;
        long newDenominator = denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction subtract(Fraction other) {
        return add(new Fraction(-other.numerator, denominator));
    }

    public Fraction multiply(Fraction other) {
        return new Fraction(numerator * other.numerator, denominator * other.denominator);
    }

    public Fraction divide(Fraction other) {
        return multiply(new Fraction(other.denominator, other.numerator));
    }

    public String toString() {
        return numerator + (isWhole() ? "" : "/" + denominator);
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    public boolean isWhole() {
        return denominator == 1;
    }

    public Fraction abs() {
        if (numerator < 0) {
            return new Fraction(-numerator, denominator);
        }
        return this;
    }
}
