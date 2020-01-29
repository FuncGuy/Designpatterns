/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package maths;

import java.util.*;

public class PrimeSieve {
    public static boolean[] sieve(int numbers) {
        boolean[] sieve = new boolean[numbers];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;

        for (int i = 2; i < sieve.length; i++) {
            if (sieve[i]) {
                for (int j = i + i; j < sieve.length; j += i) {
                    sieve[j] = false;
                }
            }
        }
        return sieve;
    }

    public static int[] ints(int upto) {
        boolean[] sieve = sieve(upto);
        int primeNumbers = 0;
        for (boolean b : sieve) {
            if (b) primeNumbers++;
        }
        System.out.println("primeNumbers = " + primeNumbers);
        int[] primes = new int[primeNumbers];
        int primeIndex = 0;
        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i]) {
                primes[primeIndex++] = i;
            }
        }
        return primes;
    }
}
