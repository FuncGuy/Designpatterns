/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package strategy.exercise1;

import org.junit.*;
import util.*;

import java.util.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class StrategyTest {
    @Test
    public void testThatWeHaveStrategyObjects() throws ClassNotFoundException {
        Collection<Class<? extends TaxStrategy>> concreteStrategies = TestHelpers.getClassesExtending(TaxStrategy.class);
        assertEquals("We expect three concrete strategies to exist, one for each tax type", 3, concreteStrategies.size());
    }

    @Test
    public void testTaxStrategyMethods() throws Exception {
        TaxPayer heinz, maxsol, family;
        heinz = new TaxPayer(TaxPayer.EMPLOYEE, 50000);
        maxsol = new TaxPayer(TaxPayer.COMPANY, 100000);
        family = new TaxPayer(TaxPayer.TRUST, 30000);
        assertEquals(22500.0, heinz.extortCash(), 0.001);
        assertEquals(30000.0, maxsol.extortCash(), 0.001);
        assertEquals(10500.0, family.extortCash(), 0.001);
    }
}
