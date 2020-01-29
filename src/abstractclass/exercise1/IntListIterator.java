/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractclass.exercise1;

// DON'T CHANGE
public interface IntListIterator extends IntIterator {
    boolean hasNext();

    int next();

    boolean hasPrevious();

    int previous();

    int nextIndex();

    int previousIndex();

    void remove();

    void set(int e);

    void add(int e);
}
