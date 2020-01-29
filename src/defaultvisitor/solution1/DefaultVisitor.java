/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package defaultvisitor.solution1;

public interface DefaultVisitor extends Visitor {
    default void visit(Contact c) {
        // do nothing
    }

    default void visit(Person p) {
        visit((Contact) p);
    }

    default void visit(DistributionList dl) {
        visit((Contact) dl);
    }
}
