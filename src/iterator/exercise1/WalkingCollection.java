/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package iterator.exercise1;

import java.util.*;

public class WalkingCollection<E>
    extends AbstractCollection<E> {
    private final Collection<E> wrappedCollection;

    public WalkingCollection(Collection<E> wrappedCollection) {
        this.wrappedCollection = wrappedCollection;
    }

    public void iterate(Processor<? super E> processor) {
        // lock using a ReadLock, then iterate through collection calling
        // processor.process(e) on each element
        throw new UnsupportedOperationException("please implement");
    }

    public Iterator<E> iterator() {
        // this method should not really be called by users anymore, instead
        // they should call the iterate(Processor) method

        // return an iterator that locks a ReadLock on hasNext() and next()
        // and a WriteLock on remove().

        // Should throw IllegalMonitorStateException if a thread tries to call
        // remove() during iteration.
        throw new UnsupportedOperationException("please implement");
    }

    public int size() {
        // the size of the wrappedCollection, but wrapped with a ReadLock
        throw new UnsupportedOperationException("please implement");
    }

    public boolean add(E e) {
        // adds the element to the collection, throws IllegalMonitorStateException
        // if that thread is busy iterating
        throw new UnsupportedOperationException("please implement");
    }
}
