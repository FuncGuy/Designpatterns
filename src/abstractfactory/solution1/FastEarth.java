/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractfactory.solution1;

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
        return new Lamborghini();
    }

    public Animal createAnimal() {
        return new Cheetah();
    }
}
