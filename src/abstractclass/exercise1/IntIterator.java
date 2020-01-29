/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractclass.exercise1;

import java.util.*;
import java.util.function.*;

// DON'T CHANGE
public interface IntIterator {
    boolean hasNext();

    int next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(IntConsumer action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
