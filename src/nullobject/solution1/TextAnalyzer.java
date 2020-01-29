/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package nullobject.solution1;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class TextAnalyzer {
    private final Map<Character, Long> map;
    private static final char[] characterArray =
        "aeiouAEIOUbcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ".toCharArray();

    public TextAnalyzer(String filename) throws IOException {
        Path path = Paths.get(filename);
        map = new String(Files.readAllBytes(path), StandardCharsets.UTF_8)
            .chars()
            .filter(Character::isLetter)
            .mapToObj(i -> (char) i)
            .collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));
    }

    public Map.Entry<Character, Long> getEntry(int n) {
        Objects.checkIndex(n, characterArray.length);
        return Map.entry(characterArray[n], map.getOrDefault(characterArray[n], 0L));
    }
}
