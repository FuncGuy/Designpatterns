/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package singleton.solution3;

import org.junit.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class Singleton3Test {
    @Test
    public void makeEarthWithAnotherClassLoader() throws Exception {
        Earth old = Earth.getEarth();

        replaceEarth(FastEarth.class.getName());
        FastEarth fast = FastEarth.class.cast(Earth.getEarth());
        assertNotSame(old, fast);

        replaceEarth(SlowEarth.class.getName());
        SlowEarth slow = SlowEarth.class.cast(Earth.getEarth());
        assertNotSame(old, slow);
        assertNotSame(fast, slow);
    }

    private void replaceEarth(String earthclass) throws Exception {
        System.setProperty("earthclass", earthclass);
        Field instance = findSingletonStaticPrivateInstanceField();
        instance.setAccessible(true);
        instance.set(null, null);
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

    @Test
    public void checkEarthIsAbstract() throws NoSuchMethodException {
        assertTrue("Earth class should be abstract", Modifier.isAbstract(Earth.class.getModifiers()));
        assertTrue("Earth class spin() method should be abstract", Modifier.isAbstract(Earth.class.getMethod("spin").getModifiers()));
        assertTrue("Earth class warmUp() method should be abstract", Modifier.isAbstract(Earth.class.getMethod("warmUp").getModifiers()));
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

    private Method checkThatSingletonContainsStaticInstanceAccessorMethod(Class<? extends Earth> clazz) {
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
    public void checkThatConstructorIsProtected() {
        for (Constructor<?> constr : Earth.class.getDeclaredConstructors()) {
            assertTrue("Constructors should be protected to allow subclasses of singleton", Modifier.isProtected(constr.getModifiers()));
        }
    }

    @Test
    public void checkThatOtherMethodsAreNonStatic() throws NoSuchMethodException {
        Method spin = Earth.class.getDeclaredMethod("spin");
        assertFalse("Method spin() should not be static", Modifier.isStatic(spin.getModifiers()));
        Method warmUp = Earth.class.getDeclaredMethod("warmUp");
        assertFalse("Method warmUp() should not be static", Modifier.isStatic(warmUp.getModifiers()));
    }

    @Test
    public void checkSubClasses() {
        assertEquals("FastEarth should be a subclass of Earth", Earth.class, FastEarth.class.getSuperclass());
        assertEquals("SlowEarth should be a subclass of Earth", Earth.class, SlowEarth.class.getSuperclass());
    }

    @Test
    public void testForDoubleCheckedLocking() throws NoSuchMethodException, NoSuchFieldException {
        assertFalse("getEarth() should not be synchronized - use double-checked locking",
            Modifier.isSynchronized(Earth.class.getMethod("getEarth").getModifiers()));
        assertTrue("earth field should be volatile for double-checked locking to work correctly",
            Modifier.isVolatile(Earth.class.getDeclaredField("earth").getModifiers()));
    }
}
