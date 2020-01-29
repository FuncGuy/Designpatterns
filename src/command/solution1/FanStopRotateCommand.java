/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package command.solution1;

public class FanStopRotateCommand implements Command {
    private final Fan fan;

    public FanStopRotateCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.stopRotate();
    }
}
