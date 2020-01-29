/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package nullobject.exercise1;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class TextAnalyzer {
    private final HashMap<Character, Long> map = new HashMap<>();
    private static final char[] characterArray =
        "aeiouAEIOUbcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ".toCharArray();

    public TextAnalyzer(String filename) throws IOException {
        Path path = Paths.get(filename);
        String contents = new String(Files.readAllBytes(path),
            StandardCharsets.UTF_8);
        for (String word : contents.split("\\PL+")) {
            char[] charArray = word.toCharArray();
            IntStream.rangeClosed(0, charArray.length - 1)
                .forEach(n ->
                    map.compute(charArray[n],
                        (k, v) -> v == null ? 1 : v + 1)
                );
        }
    }

    public Map.Entry<Character, Long> getEntry(int n) {
        if (n < 0 || n > 51)
            throw new IllegalArgumentException();
        if (map.containsKey(characterArray[n])) {
            return Map.entry(characterArray[n],
                map.get(characterArray[n]));
        }
        return null;
    }
}
