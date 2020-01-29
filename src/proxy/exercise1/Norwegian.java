/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package proxy.exercise1;

public class Norwegian extends Scandinavian {
    private Lutefisk lutefisk;

    public void work() {
        System.out.println("Working hard whilst it is dark outside.");
    }

    public void learn() {
        System.out.println("Going to Crete to attend Heinz's course");
    }

    public void celebrateChristmas() {
        if (lutefisk == null) {
            lutefisk = new Lutefisk();
        }
        lutefisk.eat();
    }

    public void entertain() {
        System.out.println("Chasing reindeer");
    }
}
