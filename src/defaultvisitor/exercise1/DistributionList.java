/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package defaultvisitor.exercise1;

import java.util.*;

//DON'T CHANGE
public class DistributionList extends Contact {
    private final List<Contact> contacts = new ArrayList<>();

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void remove(Contact contact) {
        contacts.remove(contact);
    }

    public void sendMail(String msg) {
        for (Contact contact : contacts) {
            contact.sendMail(msg);
        }
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        contacts.forEach(contact -> contact.accept(visitor));
    }

    public int getNumberOfChildren() {
        return contacts.size();
    }
}
