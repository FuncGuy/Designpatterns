/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package util;

import java.io.*;
import java.net.*;
import java.util.*;

public class TestHelpers {
    private static <E> Collection<Class<? extends E>> getClassesExtending(
        Class<E> rootClass, String pattern) throws ClassNotFoundException {
        ArrayList<Class<? extends E>> classes = new ArrayList<>();
        File dir = findOriginClassPath(rootClass, pattern);
        for (String filename : dir.list()) {
            if (filename.endsWith(".class")) {
                Class<?> c = Class.forName(pattern.replace('/', '.') + "." + filename.split("\\.")[0], true, Thread.currentThread().getContextClassLoader());
                if (rootClass.isAssignableFrom(c) && c != rootClass) {
                    classes.add(c.asSubclass(rootClass));
                }
            }
        }
        return classes;
    }

    public static <E> Collection<Class<? extends E>> getClassesExtending(
        Class<E> rootClass) throws ClassNotFoundException {
        return getClassesExtending(rootClass, rootClass.getPackageName().replaceAll("\\.", "/"));
    }

    private static <E> File findOriginClassPath(Class<E> rootClass, String pattern) throws ClassNotFoundException {
        String classpathEntry = whereis(rootClass);

        File dir = new File(classpathEntry + pattern);
        System.out.println("dir = " + dir);
        if (dir.isDirectory()) {
            return dir;
        }
        throw new IllegalStateException("Could not find directory for classes");
    }

    public static String whereis(Class<?> clazz) {
        return whereis(clazz.getName(), clazz.getClassLoader());
    }

    public static String whereis(String className, ClassLoader classloader) {
        String filename = className.replace('.', '/') + ".class";
        if (classloader == null)
            classloader = ClassLoader.getSystemClassLoader();
        URL url = classloader.getResource(filename);
        // idea from http://weblogs.java.net/blog/2007/04/25/how-convert-javaneturl-javaiofile
        File file;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }

        String location = file.toString();
        location = location.substring(0,
            location.length() - className.length() -
                ".class".length());
        return location;
    }
}
