/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package state.solution1;

public abstract class Worker extends State {
    public void fire(Employee employee) {
        employee.setState(new End());
    }
}
