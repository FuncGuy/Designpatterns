/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package maths;

import java.util.*;
import java.util.concurrent.*;

public class Statistics {
    private final Collection<Integer> values = new ConcurrentLinkedQueue<>();

    public void add(int value) {
        values.add(value);
    }

    public int size() {
        return values.size();
    }

    public double getMean() {
        return values.stream()
            .mapToInt(Integer::intValue)
            .summaryStatistics().getAverage();
    }

    public double getVariance() {
        double mean = getMean();
        return values.stream()
            .mapToDouble(value -> mean - value)
            .map(value -> value * value)
            .sum() / size();
    }

    public double getStdDev() {
        return Math.sqrt(getVariance());
    }
}
