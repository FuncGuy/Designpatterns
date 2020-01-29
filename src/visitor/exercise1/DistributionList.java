/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package visitor.exercise1;

import java.util.*;
import java.util.concurrent.*;

public class DistributionList extends Contact {
    private final Collection<Contact> contacts = new ConcurrentLinkedQueue<>();

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void remove(Contact contact) {
        contacts.remove(contact);
    }

    public void sendMail(String msg) {
        contacts.forEach(contact -> contact.sendMail(msg));
    }
}
