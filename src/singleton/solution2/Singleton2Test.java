/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package singleton.solution2;

import org.junit.*;
import util.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class Singleton2Test {
    @Test
    public void newSpeedLoader() throws Exception {
        Earth earth0 = Earth.getEarth();

        makeNewEarth(2000);
        assertNotSame(earth0, Earth.getEarth());
        checkSpeed(Earth.class, 2000);

        makeNewEarth(1532);
        checkSpeed(Earth.class, 1532);
    }

    private void makeNewEarth(int speed) throws Exception {
        System.setProperty("earthspeed", String.valueOf(speed));
        Field instance = findSingletonStaticPrivateInstanceField();

        Constructor<Earth> earthConstructor;
        Earth earth1;
        try {
            // using no-args constructor
            earthConstructor = Earth.class.getDeclaredConstructor();
            earthConstructor.setAccessible(true);
            earth1 = earthConstructor.newInstance();
        } catch (NoSuchMethodException ex) {
            // using int constructor, the speed
            earthConstructor = Earth.class.getDeclaredConstructor(int.class);
            earthConstructor.setAccessible(true);
            earth1 = earthConstructor.newInstance(speed);
        }
        ReflectionHelper.setStaticFinalField(instance, earth1);
    }

    private void checkSpeed(Class<? extends Earth> clazz, int expectedSpeed) throws Exception {
        Method accessor = checkThatSingletonContainsStaticInstanceAccessorMethod(clazz);
        Object earth = accessor.invoke(null);
        Method getSpeed = clazz.getDeclaredMethod("getSpeed");
        Integer actualSpeed = (Integer) getSpeed.invoke(earth);
        assertEquals(expectedSpeed, (int) actualSpeed);
    }

    @Test
    public void callTheSpinAndWarmUpMethods() throws Exception {
        Method accessor = checkThatSingletonContainsStaticInstanceAccessorMethod(Earth.class);
        Earth earth = (Earth) accessor.invoke(null);
        earth.spin();
        earth.warmUp();
    }

    @Test
    public void testThatAccessorAlwaysReturnsSameInstance() throws Exception {
        Method accessor = checkThatSingletonContainsStaticInstanceAccessorMethod(Earth.class);
        Earth earth1 = (Earth) accessor.invoke(null);
        Earth earth2 = (Earth) accessor.invoke(null);
        assertSame("Instances returned should be the same", earth1, earth2);
    }

    public Field findSingletonStaticPrivateInstanceField() {
        for (Field field : Earth.class.getDeclaredFields()) {
            if (field.getType() == Earth.class) {
                return field;
            }
        }
        return null;
    }

    @Test
    public void checkThatSingletonContainsStaticPrivateInstanceField() {
        Field field = findSingletonStaticPrivateInstanceField();
        assertNotNull("No static private instance field found.", field);
        assertTrue("Field pointing to the Earth instance should be static", Modifier.isStatic(field.getModifiers()));
        assertTrue("Field pointing to the Earth instance should be private", Modifier.isPrivate(field.getModifiers()));
    }

    @Test
    public void checkThatSingletonContainsStaticInstanceAccessorMethod() {
        checkThatSingletonContainsStaticInstanceAccessorMethod(Earth.class);
    }

    private Method checkThatSingletonContainsStaticInstanceAccessorMethod(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getReturnType() == clazz && method.getParameterTypes().length == 0) {
                assertTrue("Method returning the Earth instance should be static", Modifier.isStatic(method.getModifiers()));
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
