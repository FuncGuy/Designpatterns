/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package command.solution2;

public class SwitchFactory {
    public static Switch make(Fan fan) {
        return new Switch(fan::startRotate, fan::stopRotate);
    }

    public static Switch make(Light light) {
        return new Switch(light::turnOn, light::turnOff);
    }
}
