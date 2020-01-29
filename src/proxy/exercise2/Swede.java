/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package proxy.exercise2;

public class Swede extends Scandinavian {
    private final Lutefisk lutefisk = new VirtualLutefisk();

    public void work() {
        System.out.println("Slaving away to pay my taxes (Jan-Nov)");
    }

    public void learn() {
        System.out.println("Going to Crete to attend Heinz's course");
    }

    public void celebrateChristmas() {
        lutefisk.eat();
    }

    public void entertain() {
        lutefisk.eat();
    }
}
