/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractfactory.solution1;

public class SlowEarth extends Earth {
    protected SlowEarth() {
    }

    public void spin() {
        System.out.println("Earth is spinning slower");
    }

    public void warmUp() {
        System.out.println("Earth is warming up slower");
    }

    public Vehicle createVehicle() {
        return new Tractor();
    }

    public Animal createAnimal() {
        return new Sloth();
    }
}
