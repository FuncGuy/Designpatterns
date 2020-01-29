/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package composite.solution1;

import java.util.*;

public class ContactIterator implements Iterator<Contact> {
    private final Deque<Iterator<Contact>> unfinishedIterators =
        new ArrayDeque<>();
    private Contact nextLeafContact = null;
    private Iterator<Contact> lastIteratorUsed = null;

    public ContactIterator(Contact contact) {
        if (contact.isLeaf()) {
            nextLeafContact = contact;
        } else {
            unfinishedIterators.push(contact.children());
        }
    }

    public boolean hasNext() {
        if (nextLeafContact == null) {
            nextLeafContact = findNextLeafContact();
        }
        return nextLeafContact != null;
    }

    private Contact findNextLeafContact() {
        while (true) {
            if (unfinishedIterators.isEmpty()) return null;
            lastIteratorUsed = unfinishedIterators.pop();
            if (lastIteratorUsed.hasNext()) {
                Contact c = lastIteratorUsed.next();
                if (c.isLeaf()) {
                    unfinishedIterators.push(lastIteratorUsed);
                    return c;
                }
                unfinishedIterators.push(lastIteratorUsed);
                unfinishedIterators.push(c.children());
            }
        }
    }

    public Contact next() {
        if (!hasNext()) throw new NoSuchElementException();
        Contact result = nextLeafContact;
        nextLeafContact = null;
        return result;
    }

    public void remove() {
        if (lastIteratorUsed == null) {
            throw new IllegalStateException("Root object was a leaf - cannot remove");
        }
        lastIteratorUsed.remove();
    }
}
