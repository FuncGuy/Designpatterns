/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package util;

public class ClassHelper {
    public static Class<?> getClass(String className) throws ClassNotFoundException {
        Class<?> caller = Which.caller(2);
        String fullName = caller.getPackageName() + "." + className;
        return Class.forName(fullName);
    }
}
