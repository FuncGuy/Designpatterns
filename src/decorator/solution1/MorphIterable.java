/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package decorator.solution1;

import java.util.*;

// this class should implement Iterable<B>
public class MorphIterable<A, B> implements Iterable<B> {
    private final Iterable<A> input;
    private final Morpher<A, B> morpher;

    public MorphIterable(Iterable<A> input, Morpher<A, B> morpher) {
        this.input = input;
        this.morpher = morpher;
    }

    public Iterator<B> iterator() {
        return new Iterator<>() {
            private final Iterator<A> wrapped = input.iterator();

            public boolean hasNext() {
                return wrapped.hasNext();
            }

            public B next() {
                return morpher.morph(wrapped.next());
            }

            public void remove() {
                wrapped.remove();
            }
        };
    }

    public interface Morpher<A, B> {
        B morph(A a);
    }
}
