/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractfactory.exercise1;

import org.junit.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class AbstractFactoryTest {
    @Test
    public void testSlowEarth() {
        Earth earth = new SlowEarth();
        Animal animal = earth.createAnimal();
        Vehicle vehicle = earth.createVehicle();
        assertEquals("Sloth", animal.getClass().getSimpleName());
        assertEquals("Tractor", vehicle.getClass().getSimpleName());
    }

    @Test
    public void testFastEarth() {
        Earth earth = new FastEarth();
        Animal animal = earth.createAnimal();
        Vehicle vehicle = earth.createVehicle();
        assertEquals("Cheetah", animal.getClass().getSimpleName());
        assertEquals("Lamborghini", vehicle.getClass().getSimpleName());
    }

    @Test
    public void testCreationUsingProperties() throws Exception {
        resetEarthField();
        System.getProperties().setProperty("earthclass",
            getClass().getPackageName() + ".SlowEarth");
        Earth slow_earth = Earth.getEarth();
        assertEquals(SlowEarth.class, slow_earth.getClass());

        resetEarthField();
        System.getProperties().setProperty("earthclass",
            getClass().getPackageName() + ".FastEarth");
        Earth fast_earth = Earth.getEarth();
        assertEquals(FastEarth.class, fast_earth.getClass());
    }

    private void resetEarthField() throws NoSuchFieldException, IllegalAccessException {
        // initialValue earth field
        Field earthField = Earth.class.getDeclaredField("earth");
        earthField.setAccessible(true);
        earthField.set(null, null);
    }
}
