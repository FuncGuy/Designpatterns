/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractclass.solution1;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public interface IntCollection extends IntIterable {
    int size();

    boolean isEmpty();

    boolean contains(int o);

    IntIterator iterator();

    int[] toArray();

    boolean add(int e);

    boolean remove(int o);

    boolean containsAll(IntCollection c);

    boolean addAll(IntCollection c);

    boolean removeAll(IntCollection c);

    default boolean removeIf(IntPredicate filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final IntIterator each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    boolean retainAll(IntCollection c);

    void clear();

    default IntStream stream() {
        return StreamSupport.intStream(spliterator(), false);
    }

    default IntStream parallelStream() {
        return StreamSupport.intStream(spliterator(), true);
    }
}
