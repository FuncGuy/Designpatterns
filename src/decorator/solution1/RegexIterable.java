/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package decorator.solution1;

import java.util.*;
import java.util.stream.*;

// this class should implement Iterable<T>
public class RegexIterable<T> implements Iterable<T> {
    private final Collection<T> filteredItems;

    // at construction, we build up a new list and add all those
    // objects whose toString() method matches the regular expression
    public RegexIterable(Iterable<T> it, String regex) {
        filteredItems = StreamSupport.stream(it.spliterator(), false)
            .filter(t -> String.valueOf(t).matches(regex))
            .collect(Collectors.toUnmodifiableList());
    }

    // Our iterator then simply walks over that list.
    public Iterator<T> iterator() {
        return filteredItems.iterator();
    }
}
