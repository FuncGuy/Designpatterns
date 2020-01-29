/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package maths;

import java.math.*;
import java.util.*;
import java.util.stream.*;

public class Numbers {
    public static int reverse(int number) {
        int result = 0;
        while (number != 0) {
            result += number % 10;
            number /= 10;
            if (number != 0) result *= 10;
        }
        return result;
    }

    public static BigInteger reverse(BigInteger number) {
        char[] digits = number.toString().toCharArray();
        for (int i = 0; i < digits.length / 2; i++) {
            char temp = digits[i];
            digits[i] = digits[digits.length - i - 1];
            digits[digits.length - i - 1] = temp;
        }
        return new BigInteger(new String(digits));
    }

    public static boolean isPalindrome(BigInteger number) {
        char[] digits = number.toString().toCharArray();
        for (int i = 0; i < digits.length / 2; i++) {
            if (digits[i] != digits[digits.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static long digitalSum(BigInteger number) {
        String digits = number.toString();
        long sum = 0;
        int length = digits.length();
        for (int i = 0; i < length; i++) {
            sum += digits.charAt(i) - '0';
        }
        return sum;
    }

    public static long digitalSum(long number) {
        long sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public static long findLCD(long a, long b) {
        List<Long> factors_a = Factorize.factors(a).boxed().collect(Collectors.toList());
        List<Long> factors_b = Factorize.factors(b).boxed().collect(Collectors.toList());
        Collection<Long> common = Util.intersection(factors_a, factors_b);
        return common.stream().reduce(1L, (l1, l2) -> l1 * l2);
    }

    public static long findLCD(long... numbers) {
        Set<Long> factors0 = Factorize.factors(numbers[0]).boxed().collect(Collectors.toSet());
        System.out.println("factors0 = " + factors0);
        for (int i = 1; i < numbers.length; i++) {
            List<Long> factorsn = Factorize.factors(numbers[i]).boxed().collect(Collectors.toList());
            System.out.println("factorsn = " + factorsn);
            factors0.retainAll(factorsn);
        }
        System.out.println("factors0 = " + factors0);
        return factors0.stream().reduce(1L, (l1, l2) -> l1 * l2);
    }

    public static long findHCF(long... numbers) {
        Collection<Long> factors0 = Factorize.factors(numbers[0]).boxed().collect(Collectors.toList());
        for (int i = 1; i < numbers.length; i++) {
            List<Long> factorsn = Factorize.factors(numbers[i]).boxed().collect(Collectors.toList());
            factors0 = Util.intersection(factors0, factorsn);
        }
        return factors0.stream().reduce(1L, (l1, l2) -> l1 * l2);
    }

    public static long findLCM(long a, long b) {
        Collection<Long> factors0 = Factorize.factors(a).boxed().collect(Collectors.toList());
        List<Long> factorsn = Factorize.factors(b).boxed().collect(Collectors.toList());
        factors0 = Util.union(factors0, factorsn);
        return factors0.stream().reduce(1L, (l1, l2) -> l1 * l2);
    }

    public static long findLCM(long... numbers) {
        return LongStream.of(numbers).reduce(1, Numbers::findLCM);
    }

    public static int digitize(long number) {
        int fingerprint = 0;
        while (number != 0) {
            fingerprint |= 1 << (number % 10);
            number /= 10;
        }
        return fingerprint;
    }

    public static boolean isPermutation(long n0, long n1) {
        long digits0 = countDigits(n0);
        long digits1 = countDigits(n1);
        return digits0 == digits1;
    }

    /**
     * We have 6 bits for counting individual digits - should be
     * enough, since the longest long would have no more than 20
     * digits anyway
     */
    public static long countDigits(long number) {
        long result = 0;
        while (number != 0) {
            long digit = number % 10;
            long count = (result >>> (6 * digit)) & 0b111111L;
            long mask = ((0b111111L) << (6 * digit)) ^ -1;
            count++;
            count = count << (6 * digit);
            result = result & mask;
            result = result | count;
            number = number / 10;
        }
        return result;
    }

    public static int pow(int n, int exp) {
        if (exp == 0) return 1;
        return n * pow(n, exp - 1);
    }

    public static long factorial(long number) {
        if (number == 0) return 1;
        return number * factorial(number - 1);
    }

    public static long sum(long upto) {
        return upto * (upto + 1) / 2;
    }
}
