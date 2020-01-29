/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package singleton.exercise2;

/**
 * Please add a getSpeed() method to return the speed as an int.
 * This should be passed in as a JVM system property earthspeed.
 */
public class Earth {
    private static final Earth earth = new Earth();

    public static Earth getEarth() {
        return earth;
    }

    private Earth() {
    }

    public void spin() {
        System.out.println("Earth is spinning at ???");
    }

    public void warmUp() {
        System.out.println("Earth is warming up");
    }
}
