/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package templatemethod.solution1;

public abstract class TaxPayer {
    /**
     * This will become our template method.
     */
    public final double calculateTax() {
        if (isNotTaxable()) {
            return 0.0;
        }
        return getIncome() * getTaxRate();
    }

    protected abstract boolean isNotTaxable();

    protected abstract double getIncome();

    protected abstract double getTaxRate();
}
