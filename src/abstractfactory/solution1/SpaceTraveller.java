/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractfactory.solution1;

/**
 * Call this with
 * -Dearthclass=abstractfactory.SlowEarth
 * to see a Tractor and a Sloth and with
 * -Dearthclass=abstractfactory.FastEarth
 * to see a Lamborghini and a Cheetah.
 */
//DON'T CHANGE
public class SpaceTraveller {
    public static void main(String... args) {
        Vehicle car = Earth.getEarth().createVehicle();
        Animal pet = Earth.getEarth().createAnimal();

        car.drive();
        pet.sprint();
    }
}
