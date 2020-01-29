/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package iterator.solution1;

import java.util.*;
import java.util.concurrent.locks.*;

public class WalkingCollection<E>
    extends AbstractCollection<E> {
    private final Collection<E> wrappedCollection;
    private final ReentrantReadWriteLock rwlock =
        new ReentrantReadWriteLock();

    public WalkingCollection(Collection<E> wrappedCollection) {
        this.wrappedCollection = wrappedCollection;
    }

    public void iterate(Processor<? super E> processor) {
        rwlock.readLock().lock();
        try {
            for (E e : wrappedCollection) {
                if (!processor.process(e)) break;
            }
        } finally {
            rwlock.readLock().unlock();
        }
    }

    public Iterator<E> iterator() {
        rwlock.readLock().lock();
        try {
            final Iterator<E> wrappedIterator =
                wrappedCollection.iterator();
            return new Iterator<>() {
                public boolean hasNext() {
                    rwlock.readLock().lock();
                    try {
                        return wrappedIterator.hasNext();
                    } finally {
                        rwlock.readLock().unlock();
                    }
                }

                public E next() {
                    rwlock.readLock().lock();
                    try {
                        return wrappedIterator.next();
                    } finally {
                        rwlock.readLock().unlock();
                    }
                }

                public void remove() {
                    checkForIteration();
                    rwlock.writeLock().lock();
                    try {
                        wrappedIterator.remove();
                    } finally {
                        rwlock.writeLock().unlock();
                    }
                }
            };
        } finally {
            rwlock.readLock().unlock();
        }
    }

    public int size() {
        rwlock.readLock().lock();
        try {
            return wrappedCollection.size();
        } finally {
            rwlock.readLock().unlock();
        }
    }

    public boolean add(E e) {
        checkForIteration();
        rwlock.writeLock().lock();
        try {
            return wrappedCollection.add(e);
        } finally {
            rwlock.writeLock().unlock();
        }
    }

    private void checkForIteration() {
        if (rwlock.getReadHoldCount() > 0)
            throw new IllegalMonitorStateException(
                "Cannot lock writeLock whilst holding readLock");
    }
}
