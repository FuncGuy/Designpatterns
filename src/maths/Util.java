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

public class Util {
    static long addDigits(BigInteger number) {
        String digits = number.toString();
        long total = 0;
        for (int i = 0; i < digits.length(); i++) {
            total += digits.charAt(i) - '0';
        }
        return total;
    }

    public static <E> Collection<E> intersection(Collection<E> col1, Collection<E> col2) {
        Collection<E> copy1 = new ArrayList<>(col1); //1,2,3,4
        Collection<E> copy2 = new ArrayList<>(col2); // 3,4,5,6
        next:
        for (Iterator<E> it1 = copy1.iterator(); it1.hasNext(); ) {
            E next = it1.next();
            for (Iterator<E> it2 = copy2.iterator(); it2.hasNext(); ) {
                E e = it2.next();
                if (e.equals(next)) {
                    it2.remove();
                    continue next;
                }
            }
            it1.remove();
        }
        return copy1;
    }

    // 2,2,2,3 and 2,2,3,3 - > 2,2,2,3,3
    public static <E> Collection<E> union(Collection<E> col1, Collection<E> col2) {
        Collection<E> copy2 = new ArrayList<>(col2);
        Collection<E> result = new ArrayList<>();
        next:
        for (E next : col1) {
            result.add(next);
            for (Iterator<E> it2 = copy2.iterator(); it2.hasNext(); ) {
                E e = it2.next();
                if (e.equals(next)) {
                    it2.remove();
                    continue next;
                }
            }
        }
        result.addAll(copy2);
        return result;
    }
}
