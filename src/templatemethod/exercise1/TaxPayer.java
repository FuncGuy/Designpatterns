/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package templatemethod.exercise1;

public abstract class TaxPayer {
    /**
     * This will become our template method.
     */
    public abstract double calculateTax();
}
