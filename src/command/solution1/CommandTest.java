/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package command.solution1;

import org.junit.*;

import java.lang.reflect.*;
import java.util.*;

import static org.junit.Assert.*;
import static util.TestHelpers.*;

//DON'T CHANGE
public class CommandTest {
    @Test
    public void testThatCommandLeftUnchanged() {
        assertTrue("Command must remain an interface", Command.class.isInterface());
        assertEquals("Command should only have one method", 1, Command.class.getDeclaredMethods().length);
        try {
            Command.class.getDeclaredMethod("execute");
        } catch (NoSuchMethodException e) {
            fail("Command needs to keep the execute() method");
        }
        assertEquals("Command should not extend any other interfaces", 0, Command.class.getInterfaces().length);
    }

    @Test
    public void testThatFanLeftUnchanged() {
        assertFalse("Fan must remain a class", Fan.class.isInterface());
        assertEquals("Fan must keep on extending Object", Object.class, Fan.class.getSuperclass());
        assertEquals("Fan should not implement any interfaces", 0, Fan.class.getInterfaces().length);
        for (Method method : Fan.class.getDeclaredMethods()) {
            assertEquals("Fan methods do not take parameters", 0, method.getParameterTypes().length);
            assertTrue("Only two fan methods allowed - startRotate() and stopRotate()", method.getName().equals("startRotate") || method.getName().equals("stopRotate"));
            assertTrue("Fan methods should be public", Modifier.isPublic(method.getModifiers()));
        }
        assertEquals("Fan should have no fields", 0, Fan.class.getDeclaredFields().length);
    }

    @Test
    public void testThatLightLeftUnchanged() {
        assertFalse("Light must remain a class", Light.class.isInterface());
        assertEquals("Light must keep on extending Object", Object.class, Light.class.getSuperclass());
        assertEquals("Light should not implement any interfaces", 0, Light.class.getInterfaces().length);
        for (Method method : Light.class.getDeclaredMethods()) {
            assertEquals("Light methods do not take parameters", 0, method.getParameterTypes().length);
            assertTrue("Only two light methods allowed - turnOn() and turnOff()", method.getName().equals("turnOn") || method.getName().equals("turnOff"));
            assertTrue("Light methods should be public", Modifier.isPublic(method.getModifiers()));
        }
        assertEquals("Light should have no fields", 0, Light.class.getDeclaredFields().length);
    }

    @Test
    public void testThatSwitchIsCorrect() {
        assertFalse("Switch should remain a class", Switch.class.isInterface());
        assertTrue("Switch should remain final", Modifier.isFinal(Switch.class.getModifiers()));
        assertEquals("Switch must keep on extending Object", Object.class, Switch.class.getSuperclass());
        assertEquals("Switch should not implement any interfaces", 0, Switch.class.getInterfaces().length);
        for (Method method : Switch.class.getDeclaredMethods()) {
            assertEquals("Switch methods do not take parameters", 0, method.getParameterTypes().length);
            assertTrue("Only two switch methods allowed - flipUp() and flipDown()", method.getName().equals("flipUp") || method.getName().equals("flipDown"));
            assertTrue("Switch methods should be public", Modifier.isPublic(method.getModifiers()));
        }
        assertTrue("Switch should have fields", Switch.class.getDeclaredFields().length > 0);

        for (Field field : Switch.class.getDeclaredFields()) {
            assertNotSame("Switch should not know about Fan", field.getType(), Fan.class);
            assertNotSame("Switch should not know about Light", field.getType(), Light.class);
        }

        for (Constructor<?> constr : Switch.class.getDeclaredConstructors()) {
            for (Class<?> parameterType : constr.getParameterTypes()) {
                assertNotSame("Switch should not know about Fan", parameterType, Fan.class);
                assertNotSame("Switch should not know about Light", parameterType, Light.class);
            }
        }
    }

    @Test
    public void countNumberOfSubclasses() throws ClassNotFoundException {
        Collection<Class<? extends Command>> commands = getCommandClasses();
        assertEquals(1 << 2, commands.size());
    }

    private Collection<Class<? extends Command>> getCommandClasses() throws ClassNotFoundException {
        return getClassesExtending(Command.class);
    }

    @Test
    public void testFanSwitchCreation() {
        class TestFan extends Fan {
            private String lastMethod = null;

            public void startRotate() {
                lastMethod = "startRotate";
            }

            public void stopRotate() {
                lastMethod = "stopRotate";
            }
        }
        TestFan fan = new TestFan();
        assertNull(fan.lastMethod);

        Switch sw = SwitchFactory.make(fan);
        assertNull(fan.lastMethod);

        sw.flipUp();
        assertEquals("startRotate", fan.lastMethod);
        sw.flipUp();
        assertEquals("startRotate", fan.lastMethod);
        sw.flipDown();
        assertEquals("stopRotate", fan.lastMethod);
        sw.flipUp();
        assertEquals("startRotate", fan.lastMethod);
    }

    @Test
    public void testLightSwitchCreation() {
        class TestLight extends Light {
            private String lastMethod = null;

            public void turnOn() {
                lastMethod = "turnOn";
            }

            public void turnOff() {
                lastMethod = "turnOff";
            }
        }
        TestLight light = new TestLight();
        assertNull(light.lastMethod);

        Switch sw = SwitchFactory.make(light);
        assertNull(light.lastMethod);

        sw.flipUp();
        assertEquals("turnOn", light.lastMethod);
        sw.flipUp();
        assertEquals("turnOn", light.lastMethod);
        sw.flipDown();
        assertEquals("turnOff", light.lastMethod);
        sw.flipUp();
        assertEquals("turnOn", light.lastMethod);
    }
}
