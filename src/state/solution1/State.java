/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package state.solution1;

public abstract class State {
    public static final State PROGRAMMER = new Programmer();
    public static final State MANAGER = new Manager();
    public static final State RETIREE = new Retiree();
    public static final State END = new End();

    public int pay(Employee employee) {
        return 0;
    }

    public void advance(Employee employee) {
    }

    public void fire(Employee employee) {
    }
}
