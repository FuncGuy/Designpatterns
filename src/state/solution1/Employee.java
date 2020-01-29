/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package state.solution1;

public class Employee {
    private State state;

    public Employee() {
        setState(State.PROGRAMMER);
    }

    public int pay() {
        return state.pay(this);
    }

    public void advance() {
        state.advance(this);
    }

    public void fire() {
        state.fire(this);
    }

    void setState(State state) {
        Employee.this.state = state;
    }
}
