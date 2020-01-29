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
public interface IntIterable {
    IntIterator iterator();

    default void forEach(IntConsumer action) {
        Objects.requireNonNull(action);
        for (IntIterator it = iterator(); it.hasNext(); ) {
            action.accept(it.next());
        }
    }

    default Spliterator.OfInt spliterator() {
        return new IntIteratorSpliterator(iterator(), 0);
    }
}
