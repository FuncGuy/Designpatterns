/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package iterator.solution1;

//DON'T CHANGE
public class AddProcessor<N extends Number>
    implements Processor<N> {
    private double total = 0;

    public boolean process(N n) {
        total += n.doubleValue();
        return true;
    }

    public double getTotal() {
        return total;
    }

    public void reset() {
        total = 0;
    }
}

