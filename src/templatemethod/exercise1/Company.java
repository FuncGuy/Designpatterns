/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package templatemethod.exercise1;

public class Company extends TaxPayer {
    private final boolean nonProfit;
    private final double income;

    public Company(boolean nonProfit, double income) {
        this.nonProfit = nonProfit;
        this.income = income;
    }

    private boolean isNonProfit() {
        return nonProfit;
    }

    public double calculateTax() {
        if (isNonProfit() || income < 0) {
            return 0.0;
        }
        return income * 0.29;
    }
}
