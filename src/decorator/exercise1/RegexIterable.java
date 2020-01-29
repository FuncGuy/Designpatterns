/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package decorator.exercise1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.StreamSupport.stream;

public class RegexIterable<T> implements Iterable<T> {
    // at construction, we build up a new list and add all those
    // objects whose toString() method matches the regular expression
    // Our iterator then simply walks over that list.  remove() should not be
    // allowed

    private final Iterable<T> filtered;

    public RegexIterable(Iterable<T> it, String regex) {
        filtered =
            stream(it.spliterator(), false)
            .filter(t -> valueOf(t).matches(regex))
            .collect(toUnmodifiableList());
    }

    public Iterator<T> iterator() {
        return filtered.iterator();
    }

}


// 1) To build up a new list
// 2) objects whose toString() method matched the regEx then
// 3)  our iterator then simply walks over that list.
// remove should not be allowed.
