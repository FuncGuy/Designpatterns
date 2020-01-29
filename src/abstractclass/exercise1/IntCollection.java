/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractclass.exercise1;

import java.util.stream.*;

public interface IntCollection extends IntIterable {
    boolean contains(int i);

    int size();

    default IntStream stream() {
        return StreamSupport.intStream(spliterator(), false);
    }

    // TODO: Change the java.util.Collection methods below to
    // TODO: work for IntCollection (some won't make sense)
    /*
    boolean isEmpty();
    Iterator<E> iterator();
    Object[] toArray();
    <T> T[] toArray(T[] a);
    boolean add(E e);
    boolean remove(Object o);
    boolean containsAll(Collection<?> c);
    boolean addAll(Collection<? extends E> c);
    boolean removeAll(Collection<?> c);
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }
    boolean retainAll(Collection<?> c);
    void clear();
    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
    */
}
