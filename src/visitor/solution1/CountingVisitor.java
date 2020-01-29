/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package visitor.solution1;

import maths.*;

import java.util.concurrent.atomic.*;

/**
 * This must count how many leaves there are in structure, how many distribution
 * lists and what the average number of contacts in a distribution list.
 * <p/>
 * Use the Statistics class to work out the mean and variance for the list
 * lengths.
 */
public class CountingVisitor implements Visitor {
    private final LongAdder leaves = new LongAdder();
    private final Statistics stats = new Statistics();

    public void visit(Person p) {
        leaves.increment();
    }

    public void visit(DistributionList dl) {
        stats.add(dl.getNumberOfChildren());
    }

    public int getNumberOfLeaves() {
        return leaves.intValue();
    }

    public int getNumberOfComposites() {
        return stats.size();
    }

    public double getAverageNumberOfChildrenPerComposite() {
        return stats.getMean();
    }

    public double getVarianceNumberOfChildrenPerComposite() {
        return stats.getVariance();
    }
}
