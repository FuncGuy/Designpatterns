/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package memento.exercise2;

import org.junit.*;

import java.io.*;
import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class MementoTest {
    @Test
    public void testClassStructure() throws ReflectiveOperationException {
        assertTrue("Employee needs to be Serializable", Serializable.class.isAssignableFrom(Employee.class));

        try {
            Method writeReplace = Employee.class.getDeclaredMethod("writeReplace");
            assertEquals("writeReplace() return type should be Object", Object.class, writeReplace.getReturnType());
            assertTrue("writeReplace() should be private", Modifier.isPrivate(writeReplace.getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("Employee class needs a writeReplace() method to return the MementoImpl");
        }
        for (Class<?> clazz : Employee.class.getDeclaredClasses()) {
            if (clazz.getSimpleName().equals("MementoImpl")) {
                assertTrue("Employee.MementoImpl needs to be Serializable", Serializable.class.isAssignableFrom(clazz));
                try {
                    Method readResolve = clazz.getDeclaredMethod("readResolve");
                    assertEquals("readResolve() return type should be Object", Object.class, readResolve.getReturnType());
                    assertTrue("readResolve() should be private", Modifier.isPrivate(readResolve.getModifiers()));
                } catch (NoSuchMethodException e) {
                    fail("Employee.MementoImpl needs a readResolve() method to convert back to an Employee");
                }
                break;
            }
            fail("Could not find MementoImpl inner class");
        }
    }

    @Test
    public void testSerialization() throws Exception {
        Employee[] employees = {
            new Employee(), new Employee(), new Employee()
        };
        employees[0].promote();
        employees[0].promote();
        employees[0].promote();
        employees[1].pay();
        employees[1].pay();
        employees[1].pay();
        employees[2].promote();
        employees[2].promote();
        employees[2].promote();
        employees[2].pay();
        employees[2].pay();
        employees[2].pay();

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
            for (Employee employee : employees) {
                out.writeObject(employee);
            }
        }
        byte[] serializedEmployees = bout.toByteArray();
        String serializedEmployeesAsString = new String(serializedEmployees);
        System.out.println("Serialized form: " + serializedEmployeesAsString);
        assertTrue("writeReplace() needs to return the MementoImpl", serializedEmployeesAsString.contains("Employee$MementoImpl"));

        try (ObjectInputStream in = new ObjectInputStream(
            new ByteArrayInputStream(serializedEmployees)
        )) {
            for (int i = 0; i < employees.length; i++) {
                assertEquals("Serialization mismatch for employee " + i, employees[i], in.readObject());
            }
        }
    }
}
