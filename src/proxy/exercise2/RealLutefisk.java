/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package proxy.exercise2;

//DON'T CHANGE
public class RealLutefisk implements Lutefisk {
    public RealLutefisk() {
        System.out.println("Lutefisk created!");
    }

    public void eat() {
        System.out.println("You will regret eating me!");
    }
}
