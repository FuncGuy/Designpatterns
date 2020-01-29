/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package util;

import sun.misc.*;

import java.lang.reflect.*;

public class ReflectionHelper {
    private static Unsafe unsafe;

    static {
        try {
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            unsafe = (Unsafe) theUnsafeField.get(null);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public static void setStaticFinalField(Field field, Object value) throws Exception {
        Object staticFieldBase = unsafe.staticFieldBase(field);
        unsafe.putObject(staticFieldBase, unsafe.staticFieldOffset(field), value);
    }
}
