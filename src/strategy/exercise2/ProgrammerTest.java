/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package strategy.exercise2;

import org.junit.*;

import java.util.stream.*;

import static org.junit.Assert.*;

public class ProgrammerTest {
    private final ConsultingHouse cando = new ConsultingHouse(
        new Programmer("Marc Fleury", 200_000_000, "Javascript", "Delphi", "VisualBasic"),
        new Programmer("John T Davies", 200_000_000, "Java", "Assembler", "C"),
        new Programmer("Rod Johnson", 400_000_000, "Java", "Smalltalk"),
        new Programmer("Mark Shuttleworth", 400_000_000, "HTML"),
        new Programmer("Peter Lawrey", 1_000_000, "Java", "Assembler"),
        new Programmer("Martin Fowler", 1_000_000, "Java", "C++"),
        new Programmer("Martin Thompson", 1_000_000, "Java", "C++", "Clojure", "Scheme", "QuickBasic"),
        new Programmer("Kirk Pepperdine", 1_000_000, "Java", "Smalltalk")
    );

    @Test
    public void testRichList() {
        System.out.println("ProgrammerTest.testRichList");
        Programmer[] programmers = cando.getProgrammersByRichestSmartest()
            .toArray(Programmer[]::new);
        Stream.of(programmers).forEach(System.out::println);
        int index = 0;
        assertEquals("Rod Johnson", programmers[index++].getName());
        assertEquals("Mark Shuttleworth", programmers[index++].getName());
        assertEquals("John T Davies", programmers[index++].getName());
        assertEquals("Marc Fleury", programmers[index++].getName());
        assertEquals("Martin Thompson", programmers[index++].getName());
        assertEquals("Kirk Pepperdine", programmers[index++].getName());
        assertEquals("Martin Fowler", programmers[index++].getName());
        assertEquals("Peter Lawrey", programmers[index++].getName());
        System.out.println();
    }

    @Test
    public void testSmartList() {
        System.out.println("ProgrammerTest.testSmartList");
        Programmer[] programmers = cando.getProgrammersBySmartestRichest()
            .toArray(Programmer[]::new);
        Stream.of(programmers).forEach(System.out::println);
        int index = 0;
        assertEquals("Martin Thompson", programmers[index++].getName());
        assertEquals("John T Davies", programmers[index++].getName());
        assertEquals("Marc Fleury", programmers[index++].getName());
        assertEquals("Rod Johnson", programmers[index++].getName());
        assertEquals("Kirk Pepperdine", programmers[index++].getName());
        assertEquals("Martin Fowler", programmers[index++].getName());
        assertEquals("Peter Lawrey", programmers[index++].getName());
        assertEquals("Mark Shuttleworth", programmers[index++].getName());
        System.out.println();
    }

    @Test
    public void testNaturalList() {
        System.out.println("ProgrammerTest.testNaturalList");
        Programmer[] programmers = cando.getProgrammersByName()
            .toArray(Programmer[]::new);
        Stream.of(programmers).forEach(System.out::println);
        int index = 0;
        assertEquals("John T Davies", programmers[index++].getName());
        assertEquals("Kirk Pepperdine", programmers[index++].getName());
        assertEquals("Marc Fleury", programmers[index++].getName());
        assertEquals("Mark Shuttleworth", programmers[index++].getName());
        assertEquals("Martin Fowler", programmers[index++].getName());
        assertEquals("Martin Thompson", programmers[index++].getName());
        assertEquals("Peter Lawrey", programmers[index++].getName());
        assertEquals("Rod Johnson", programmers[index++].getName());
        System.out.println();
    }
}
