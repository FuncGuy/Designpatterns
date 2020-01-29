/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package proxy.exercise2;

import java.util.*;

//DON'T CHANGE
public class Scandinavia {
    private final List<Scandinavian> citizens = new ArrayList<>();

    public void addCitizen(Scandinavian per) {
        citizens.add(per);
    }

    public void normalTime() {
        for (Scandinavian s : citizens) {
            s.work();
            s.learn();
        }
    }

    public void christmasTime() {
        citizens.forEach(Scandinavian::celebrateChristmas);
    }
}
