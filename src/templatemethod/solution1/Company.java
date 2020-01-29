/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package templatemethod.solution1;

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

    protected boolean isNotTaxable() {
        return isNonProfit() || income < 0;
    }

    protected double getIncome() {
        return income;
    }

    protected double getTaxRate() {
        return 0.29;
    }
}
