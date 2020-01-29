/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package decorator.exercise1;

import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toUnmodifiableList;

// this class should implement Iterable<T>
public class ThreadSafeIterable<T> implements Iterable<T> {

    private final Iterable<T> delegate;

    // synchronize on the lock and copy the source into a new collection
    public ThreadSafeIterable(Iterable<T> source, Object lock) {
        synchronized (lock) {
            delegate = copy(source);
        }
    }

    // lock() the Java 5 lock and copy the source into a new collection
    public ThreadSafeIterable(Iterable<T> source, Lock lock) {
        lock.lock();
        try {
            delegate = copy(source);
        } finally {
            lock.unlock();
        }
    }

    private Iterable<T> copy(Iterable<T> source) {
        return StreamSupport.stream(source.spliterator(), false)
            .collect(toUnmodifiableList());
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }
}
