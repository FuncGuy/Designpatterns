/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package iterator.exercise1;

import java.util.*;

//DON'T CHANGE
public class CompositeProcessor<E>
    implements Processor<E> {
    private final List<Processor<? super E>> processors =
        new ArrayList<>();

    public void add(Processor<? super E> processor) {
        processors.add(processor);
    }

    public boolean process(E e) {
        for (Processor<? super E> processor : processors) {
            if (!processor.process(e)) return false;
        }
        return true;
    }
}
