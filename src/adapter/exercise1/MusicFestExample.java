/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package adapter.exercise1;

public class MusicFestExample {
    public static void main(String... args) {
        MusicFest fest = new MusicFest();
        fest.addSinger(new Bass());
        fest.addSinger(new Soprano());
        // TODO: write the Rapper adapters
        // fest.addSinger(new RapperObjectAdapter(new Rapper()));
        // fest.addSinger(new RapperClassAdapter());
        fest.singAll();
    }
}
