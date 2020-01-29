/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package proxy.exercise2;

import org.junit.*;
import util.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class VirtualProxyTest {
    @Test
    public void testOldHandWrittenVirtualLutefiskIsGone() throws Exception {
        try {
            ClassHelper.getClass("VirtualLutefisk");
            fail("Old hand-crafted VirtualLutefisk should be gone");
        } catch (ClassNotFoundException ignored) {}
    }

    @Test
    public void testDynamicVirtualLutefiskClassExists() throws Exception {
        try {
            findVirtualLutefiskClass();
        } catch (ClassCastException e) {
            fail("The VirtualLutefisk should be derived from Lutefisk");
        }
    }

    private Class<? extends Lutefisk> findVirtualLutefiskClass() {
        try {
            Swede swede = new Swede();
            for (Field field : Swede.class.getDeclaredFields()) {
                if (field.getType() == Lutefisk.class) {
                    field.setAccessible(true);
                    Lutefisk lutefisk = (Lutefisk) field.get(swede);
                    assertNotSame("No virtual lutefisk subclass found", lutefisk, Lutefisk.class);
                    assertTrue("Virtual lutefisk is not a dynamic proxy", lutefisk.getClass().getName().contains(".$Proxy"));
                    return lutefisk.getClass();
                }
            }
            fail("Lutefisk type of field missing in Swede");
            return null;
        } catch (ReflectiveOperationException ex) {
            fail("Could not find virtual lutefisk field in Swede: " + ex);
            return null; // will never be called
        }
    }
}
