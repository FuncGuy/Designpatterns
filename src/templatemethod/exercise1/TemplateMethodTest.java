/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package templatemethod.exercise1;

import org.junit.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class TemplateMethodTest {
    @Test
    public void testEmployee() {
        assertEquals(0.0, new Employee(true, 2000000).calculateTax(), 0.01);

        assertEquals(0.0, new Employee(false, 0).calculateTax(), 0.01);
        assertEquals(0.0, new Employee(false, -1000000).calculateTax(), 0.01);
        assertEquals(0.0, new Employee(false, 19999.99).calculateTax(), 0.01);

        assertEquals(2000, new Employee(false, 20000).calculateTax(), 0.01);
        assertEquals(2010, new Employee(false, 20100).calculateTax(), 0.01);

        assertEquals(5000, new Employee(false, 49999.99).calculateTax(), 0.01);
        assertEquals(5000, new Employee(false, 49999.99).calculateTax(), 0.01);

        assertEquals(12500, new Employee(false, 50000).calculateTax(), 0.01);
        assertEquals(15000, new Employee(false, 60000).calculateTax(), 0.01);
        assertEquals(24999.99, new Employee(false, 99999.99).calculateTax(), 0.01);
        assertEquals(45000, new Employee(false, 100000).calculateTax(), 0.01);
        assertEquals(45000000, new Employee(false, 100000000).calculateTax(), 0.01);
    }

    @Test
    public void testCompany() {
        assertEquals(0.0, new Company(true, 100000).calculateTax(), 0.01);
        assertEquals(0.0, new Company(true, 0).calculateTax(), 0.01);
        assertEquals(0.0, new Company(true, 1000).calculateTax(), 0.01);
        assertEquals(0.0, new Company(true, -1000).calculateTax(), 0.01);

        assertEquals(29000, new Company(false, 100000).calculateTax(), 0.01);
        assertEquals(0.0, new Company(false, 0).calculateTax(), 0.01);
        assertEquals(290, new Company(false, 1000).calculateTax(), 0.01);
        assertEquals(0.0, new Company(false, -1000).calculateTax(), 0.01);
    }

    @Test
    public void testTrust() {
        assertEquals(0.0, new Trust(true, 100000).calculateTax(), 0.01);
        assertEquals(0.0, new Trust(true, 0).calculateTax(), 0.01);
        assertEquals(0.0, new Trust(true, 1000).calculateTax(), 0.01);
        assertEquals(0.0, new Trust(true, -1000).calculateTax(), 0.01);

        assertEquals(40000, new Trust(false, 100000).calculateTax(), 0.01);
        assertEquals(0.0, new Trust(false, 0).calculateTax(), 0.01);
        assertEquals(400, new Trust(false, 1000).calculateTax(), 0.01);
        assertEquals(0.0, new Trust(false, -1000).calculateTax(), 0.01);
    }

    @Test
    public void testTaxPayerClassStructure() throws Exception {
        Method calculateTax = TaxPayer.class.getMethod("calculateTax");
        assertTrue("usually the template method is final", isFinal(calculateTax));
        assertTrue("usually the template method is public", isPublic(calculateTax));
        Method[] methods = TaxPayer.class.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.getName().equals("calculateTax")) {
                // we check the primitive operations, assuming that any other methods are primitive
                assertTrue("usually the primitive operations are protected", isProtected(method));
                assertTrue("usually the primitive operations are abstract", isAbstract(method));
            }
        }
    }

    private static boolean isAbstract(Method method) {
        return Modifier.isAbstract(method.getModifiers());
    }

    private static boolean isPublic(Method method) throws NoSuchMethodException {
        return Modifier.isPublic(method.getModifiers());
    }

    private static boolean isFinal(Method method) throws NoSuchMethodException {
        return Modifier.isFinal(method.getModifiers());
    }

    private static boolean isProtected(Method method) throws NoSuchMethodException {
        return Modifier.isProtected(method.getModifiers());
    }
}
