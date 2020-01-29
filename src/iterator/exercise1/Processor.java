/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package iterator.exercise1;

//DON'T CHANGE
public interface Processor<E> {
    /**
     * Returns "true" if we should continue iterating; "false" otherwise
     */
    boolean process(E e);
}
