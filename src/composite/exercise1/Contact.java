/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package composite.exercise1;

import java.util.*;

public abstract class Contact {
    public void add(Contact contact) {
    }

    public void remove(Contact contact) {
    }

    public abstract void sendMail(String msg);

    public boolean isLeaf() {
        throw new UnsupportedOperationException("todo");
    }

    public Iterator<Contact> children() {
        throw new UnsupportedOperationException("todo");
    }
}
