/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package maths;

import java.util.*;
import java.util.stream.*;

public class Factorize {
    private static boolean[] sieve = PrimeSieve.sieve(10_000_001);

    public static LongStream properDivisors(long number) {
        long original = number;
        Collection<Long> factors = new TreeSet<>();
        factors.add(1L);
        long upto = (long) Math.ceil((long) Math.sqrt(number));
        for (long l = 2; l <= upto; l++) {
            if (number % l == 0) {
                factors.add(l);
                factors.add(original / l);
            }
            if (number == 1) break;
        }
        return factors.stream().mapToLong(Long::longValue);
    }

    public static LongStream factors(long number) {
        number = Math.abs(number);
        Collection<Long> factors = new ArrayList<>();
        for (long l = 2; l <= number; l++) {
            while (number % l == 0) {
                factors.add(l);
                number /= l;
            }
            if (number == 1) break;
        }
        return factors.stream().mapToLong(Long::longValue);
    }

    public static Collection<Integer> factors(int number) {
        Collection<Integer> factors = new TreeSet<>();
        if (number < sieve.length && sieve[number]) {
            factors.add(number);
            return factors;
        }
        long upto = (long) Math.sqrt(number);
        for (int l = 2; l <= upto; l++) {
            while (number % l == 0) {
                factors.add(l);
                number /= l;
            }
            if (sieve[number]) {
                factors.add(number);
                break;
            }
            if (number == 1) break;
        }
        return factors;
    }
}
