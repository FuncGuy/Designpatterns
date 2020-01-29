/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package strategy.solution1;

public class TrustTaxStrategy implements TaxStrategy {
    public static final double TRUST_RATE = 0.35;
    private final TaxPayer payer;

    public TrustTaxStrategy(TaxPayer payer) {
        this.payer = payer;
    }

    public double extortCash() {
        return payer.getIncome() * TRUST_RATE;
    }
}
