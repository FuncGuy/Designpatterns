/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package strategy.solution2;

import java.util.*;

public class Programmer {
    private final String name;
    private final double salary;
    private final Collection<String> languages =
        new ArrayList<>();

    public Programmer(String name, double salary,
                      String... languages) {
        this.name = name;
        this.salary = salary;
        Collections.addAll(this.languages, languages);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Collection<String> getLanguages() {
        return Collections.unmodifiableCollection(languages);
    }

    public String toString() {
        return name + ", salary=" + salary + ", languages=" + languages;
    }
}
