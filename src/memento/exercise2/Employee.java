/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package memento.exercise2;

public class Employee {
    public enum Position {TESTER, PROGRAMMER, MANAGER}

    private int salary = 1000;
    private int balance = 2000;
    private Position position = Position.TESTER;

    public void pay() {
        balance += salary;
    }

    public void promote() {
        switch (position) {
            case TESTER:
                position = Position.PROGRAMMER;
                salary += 400;
                break;
            case PROGRAMMER:
                position = Position.MANAGER;
                salary *= 3;
                break;
            case MANAGER:
                salary *= 1.5;
                break;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Employee employee = (Employee) o;

        if (salary != employee.salary) return false;
        if (balance != employee.balance) return false;
        if (position != employee.position) return false;

        return true;
    }

    public Memento createMemento() {
        return new MementoImpl(this);
    }

    public void setMemento(Memento m) {
        MementoImpl mi = (MementoImpl) m;
        this.salary = mi.salary;
        this.balance = mi.balance;
        this.position = mi.position;
    }

    private static class MementoImpl implements Memento {
        private final int salary;
        private final int balance;
        private final Employee.Position position;

        public MementoImpl(Employee employee) {
            this.salary = employee.salary;
            this.balance = employee.balance;
            this.position = employee.position;
        }
    }
}
