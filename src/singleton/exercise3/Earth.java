/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package singleton.exercise3;

/**
 * Please make the Earth and the two methods spin() and warmUp() abstract.
 * <p/>
 * Next use the system property "earthclass" to decide what earth class to make.
 * Use the FastEarth as your default.
 */
public class Earth {
    private static final Earth earth = new Earth();

    public static Earth getEarth() {
        return earth;
    }

    private Earth() {
    }

    public void spin() {
        System.out.println("Earth is spinning");
    }

    public void warmUp() {
        System.out.println("Earth is warming up");
    }
}
