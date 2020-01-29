/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package singleton.exercise1;

import org.junit.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class Singleton1Test {
    @SuppressWarnings("static-access")
    @Test
    public void callTheSpinAndWarmUpMethods() throws Exception {
        Method accessor = findStaticInstanceAccessorMethod();
        Earth earth = (Earth) accessor.invoke(null);
        earth.spin();
        earth.warmUp();
    }

    @Test
    public void testThatAccessorAlwaysReturnsSameInstance() throws Exception {
        Method accessor = findStaticInstanceAccessorMethod();
        Earth earth1 = (Earth) accessor.invoke(null);
        Earth earth2 = (Earth) accessor.invoke(null);
        assertSame("Instances returned should be the same", earth1, earth2);
    }

    @Test
    public void checkThatSingletonContainsStaticPrivateInstanceField() {
        for (Field field : Earth.class.getDeclaredFields()) {
            if (field.getType() == Earth.class) {
                assertTrue("Field pointing to the Earth instance should be static", Modifier.isStatic(field.getModifiers()));
                assertTrue("Field pointing to the Earth instance should be private", Modifier.isPrivate(field.getModifiers()));
                return;
            }
        }
        fail("No static private instance field found.");
    }

    @Test
    public void checkThatSingletonContainsStaticInstanceAccessorMethod() {
        assertTrue("Method returning the Earth instance should be static", Modifier.isStatic(findStaticInstanceAccessorMethod().getModifiers()));
    }

    private Method findStaticInstanceAccessorMethod() {
        for (Method method : Earth.class.getDeclaredMethods()) {
            if (method.getReturnType() == Earth.class && method.getParameterTypes().length == 0) {
                return method;
            }
        }
        fail("No static accessor method found.");
        return null; // will never be called.
    }

    @Test
    public void checkThatConstructorIsPrivate() {
        for (Constructor<?> constr : Earth.class.getDeclaredConstructors()) {
            assertTrue("Constructors should be private if possible", Modifier.isPrivate(constr.getModifiers()));
        }
    }

    @Test
    public void checkThatOtherMethodsAreNonStatic() throws NoSuchMethodException {
        Method spin = Earth.class.getDeclaredMethod("spin");
        assertFalse("Method spin() should not be static", Modifier.isStatic(spin.getModifiers()));
        Method warmUp = Earth.class.getDeclaredMethod("warmUp");
        assertFalse("Method warmUp() should not be static", Modifier.isStatic(warmUp.getModifiers()));
    }
}
