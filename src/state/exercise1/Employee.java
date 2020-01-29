/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package state.exercise1;

/**
 * Code without clear state machine transitions.  It is very
 * ugly.  Your job is to use the state pattern to clean things
 * up.
 */
public class Employee {
    private int type = 0;

    public Employee() {
        type = 1; // programmer - that's what you start with
    }

    public int pay() {
        if (type == 1) { // programmer
            System.out.println("Programmer getting paid");
            return 3000;
        }
        if (type == 2) { // manager
            System.out.println("Paying lots of $$$ to manager");
            return 30000;
        }
        if (type == 3) { // retiree
            System.out.println("Handing out crumbs to retiree");
            return 5000;
        }
        return 0;
    }

    public void advance() {
        if (type < 4) type++;
    }

    public void fire() {
        if (type < 3) type = 4;
    }
}
