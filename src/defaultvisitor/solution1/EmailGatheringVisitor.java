/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package defaultvisitor.solution1;

import java.util.*;
import java.util.concurrent.*;

/**
 * This visitor must return a unique set of all emails in the contact structure
 * in alphabetical order.
 */

public class EmailGatheringVisitor implements Iterable<String>, DefaultVisitor {
    private final Collection<String> emails = new ConcurrentSkipListSet<>();

    public Iterator<String> iterator() {
        return emails.iterator();
    }

    public void visit(Person p) {
        emails.add(p.getEmail());
    }
}
