/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package strategy.solution1;

public class TaxPayer {
    public static final int COMPANY = 0;
    public static final int EMPLOYEE = 1;
    public static final int TRUST = 2;

    private final double income;
    private final TaxStrategy taxStrategy;

    public TaxPayer(int type, double income) {
        this.income = income;
        switch (type) {
            case COMPANY:
                taxStrategy = new CompanyTaxStrategy(this);
                break;
            case EMPLOYEE:
                taxStrategy = new EmployeeTaxStrategy(this);
                break;
            case TRUST:
                taxStrategy = new TrustTaxStrategy(this);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public double getIncome() {
        return income;
    }

    public double extortCash() {
        return taxStrategy.extortCash();
    }
}
