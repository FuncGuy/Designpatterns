/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package strategy.exercise2;

import java.util.*;
import java.util.stream.*;

public class ConsultingHouse {
    private final Programmer[] programmers;

    public ConsultingHouse(Programmer... programmers) {
        this.programmers = programmers.clone();
    }

    public Stream<Programmer> getProgrammersByRichestSmartest() {
        return Stream.of(programmers);
    }

    public Stream<Programmer> getProgrammersBySmartestRichest() {
        return Stream.of(programmers);
    }

    public Stream<Programmer> getProgrammersByName() {
        return Stream.of(programmers);
    }
}
