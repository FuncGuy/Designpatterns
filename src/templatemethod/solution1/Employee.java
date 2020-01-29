/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package templatemethod.solution1;

public class Employee extends TaxPayer {
    private final boolean foreignResident;
    private final double income;

    public Employee(boolean foreignResident,
                    double income) {
        this.foreignResident = foreignResident;
        this.income = income;
    }

    private boolean isForeignResident() {
        return foreignResident;
    }

    protected boolean isNotTaxable() {
        return isForeignResident();
    }

    protected double getIncome() {
        return income;
    }

    protected double getTaxRate() {
        if (income < 20000) {
            return 0.0;
        } else if (income < 50000) {
            return 0.1;
        } else if (income < 100000) {
            return 0.25;
        } else {
            return 0.45;
        }
    }
}
