/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package defaultvisitor.exercise1;

//DON'T CHANGE
public class Person extends Contact {
    private final String email;

    public Person(String email) {
        this.email = email;
    }

    public void sendMail(String msg) {
        System.out.println("To: " + email);
        System.out.println("Msg: " + msg);
        System.out.println();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getEmail() {
        return email;
    }
}
