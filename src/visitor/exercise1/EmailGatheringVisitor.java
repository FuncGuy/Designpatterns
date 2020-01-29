/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package visitor.exercise1;

import java.util.*;

/**
 * This visitor must return a unique set of all emails in the contact structure
 * in alphabetical order.
 */
public class EmailGatheringVisitor implements Iterable<String> {
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException("todo");
    }
}
