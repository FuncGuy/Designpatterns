/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package singleton.solution3;

public class FastEarth extends Earth {
    public void spin() {
        System.out.println("Earth is spinning fast");
    }

    public void warmUp() {
        System.out.println("Earth is warming up real fast");
    }
}
