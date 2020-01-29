/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package proxy.exercise1;

//DON'T CHANGE
public class ScandinvianExample {
    public static void main(String... args) {
        Scandinavia sc = new Scandinavia();
        sc.addCitizen(new Norwegian());
        sc.addCitizen(new Norwegian());
        sc.addCitizen(new Swede());
        sc.addCitizen(new Swede());
        sc.normalTime();
        sc.christmasTime();
    }
}
