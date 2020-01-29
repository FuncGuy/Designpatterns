/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractfactory.exercise1;

public class FastEarth extends Earth {
    protected FastEarth() {
    }

    public void spin() {
        System.out.println("Earth is spinning faster");
    }

    public void warmUp() {
        System.out.println("Earth is warming up faster");
    }

    public Vehicle createVehicle() {
        throw new UnsupportedOperationException("todo");
    }

    public Animal createAnimal() {
        throw new UnsupportedOperationException("todo");
    }
}
