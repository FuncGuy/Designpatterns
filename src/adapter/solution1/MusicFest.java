/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package adapter.solution1;

import java.util.*;

//DON'T CHANGE
public class MusicFest {
    private final List<Singer> singers = new ArrayList<>();

    public void addSinger(Singer singer) {
        singers.add(singer);
    }

    public void singAll() {
        singers.forEach(Singer::sing);
    }
}
