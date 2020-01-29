/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package proxy.solution1;

import org.junit.*;
import util.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class VirtualProxyTest {
    @Test
    public void testLutefiskIsInterface() throws IllegalAccessException, InstantiationException {
        assertTrue("We need to convert Lutefisk into an interface, it is our 'Subject'", Lutefisk.class.isInterface());
    }

    @Test
    public void testScandinaviansHaveReferenceToVirtualLutefisk() throws IllegalAccessException, InstantiationException {
        Class<? extends Lutefisk> virtualLutefiskClass = findVirtualLutefiskClass();
        for (Field field : Swede.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(Lutefisk.class)) {
                field.setAccessible(true);
                assertTrue("lutefisk field in Swede should be assigned to a VirtualLutefisk",
                    virtualLutefiskClass.isInstance(field.get(new Swede())));
            }
        }
        for (Field field : Norwegian.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(Lutefisk.class)) {
                field.setAccessible(true);
                assertTrue("lutefisk field in Norwegian should be assigned to a VirtualLutefisk",
                    virtualLutefiskClass.isInstance(field.get(new Norwegian())));
            }
        }
    }

    @Test
    public void testScandinaviansHaveNonNullReferenceToVirtualLutefisk() throws IllegalAccessException, InstantiationException {
        Class<? extends Lutefisk> virtualLutefiskClass = findVirtualLutefiskClass();
        for (Field field : Swede.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(Lutefisk.class)) {
                field.setAccessible(true);
                assertTrue("lutefisk field in Swede should be assigned to a VirtualLutefisk",
                    virtualLutefiskClass.isInstance(field.get(new Swede())));
            }
        }
        for (Field field : Norwegian.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(Lutefisk.class)) {
                field.setAccessible(true);
                assertTrue("lutefisk field in Norwegian should be assigned to a VirtualLutefisk",
                    virtualLutefiskClass.isInstance(field.get(new Norwegian())));
            }
        }
    }

    @Test
    public void testRealLutefiskClassExists() throws Exception {
        try {
            Class<? extends Lutefisk> realLutefiskClass =
                ClassHelper.getClass("RealLutefisk").asSubclass(Lutefisk.class);
            Lutefisk realLutefisk = realLutefiskClass.getConstructor().newInstance();
            realLutefisk.eat();
        } catch (ClassCastException e) {
            fail("The RealLutefisk should be derived from Lutefisk");
        } catch (ClassNotFoundException e) {
            fail("We need a RealLutefisk class that contains the original Lutefisk code");
        }
    }

    @Test
    public void testVirtualLutefiskClassExists() throws Exception {
        try {
            Class<? extends Lutefisk> virtualLutefiskClass = findVirtualLutefiskClass();
            Lutefisk virtualLutefisk = virtualLutefiskClass.getConstructor().newInstance();
            assertEquals("Virtual Lutefisk should only have one field, the delegate", 1, virtualLutefiskClass.getDeclaredFields().length);
            Field delegate = virtualLutefiskClass.getDeclaredFields()[0];
            assertTrue("Delegate should be of type Lutefisk", delegate.getType().isAssignableFrom(Lutefisk.class));
            delegate.setAccessible(true);
            assertNull("Initially the delegate field should be null", delegate.get(virtualLutefisk));
            virtualLutefisk.eat();
            assertNotNull("After calling eat(), the delegate field should NOT be null", delegate.get(virtualLutefisk));
        } catch (ClassCastException e) {
            fail("The VirtualLutefisk should be derived from Lutefisk");
        }
    }

    private Class<? extends Lutefisk> findVirtualLutefiskClass() {
        try {
            return ClassHelper.getClass("VirtualLutefisk").asSubclass(Lutefisk.class);
        } catch (ClassNotFoundException e) {
            fail("We need a VirtualLutefisk that constructs the real lutefisk on demand");
            return null; // will never be called.
        }
    }
}
