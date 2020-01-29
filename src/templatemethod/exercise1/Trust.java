/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package templatemethod.exercise1;

public class Trust extends TaxPayer {
    private final boolean disability;
    private final double income;

    public Trust(boolean disability, double income) {
        this.disability = disability;
        this.income = income;
    }

    public boolean isDisability() {
        return disability;
    }

    private double getTaxRate() {
        return 0.40;
    }

    public double calculateTax() {
        return disability || income < 0 ? 0.0 : income * getTaxRate();
    }
}
