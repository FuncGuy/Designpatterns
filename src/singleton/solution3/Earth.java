/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package singleton.solution3;

/**
 * Please make the Earth and the two methods spin() and warmUp() abstract.
 * <p/>
 * Next use the system property "earthclass" to decide what earth class to make.
 * Use the FastEarth as your default.
 */
public abstract class Earth {
    private static volatile Earth earth;

    public static Earth getEarth() {
        if (earth == null) {
            synchronized (Earth.class) {
                if (earth == null) {
                    try {
                        Class<? extends Earth> earthClass = Class.forName(
                            System.getProperty("earthclass", "singleton.solution3.FastEarth"), true, Thread.currentThread().getContextClassLoader()).asSubclass(Earth.class);
                        earth = earthClass.getConstructor().newInstance();
                    } catch (Exception e) {
                        System.err.println(e);
                        earth = new FastEarth();
                    }
                }
            }
        }
        return earth;
    }

    protected Earth() {
    }

    public abstract void spin();

    public abstract void warmUp();
}
