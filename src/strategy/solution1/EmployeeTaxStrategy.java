/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package strategy.solution1;

public class EmployeeTaxStrategy implements TaxStrategy {
    public static final double EMPLOYEE_RATE = 0.45;
    private final TaxPayer payer;

    public EmployeeTaxStrategy(TaxPayer payer) {
        this.payer = payer;
    }

    public double extortCash() {
        return payer.getIncome() * EMPLOYEE_RATE;
    }
}
