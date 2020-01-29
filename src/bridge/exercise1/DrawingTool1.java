/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package bridge.exercise1;

import java.util.*;

//DON'T CHANGE
public class DrawingTool1 {
    /**
     * Used to check that all the drawings were done correctly.
     */
    private static final Collection<String> drawHistory =
        new TreeSet<>();

    public void draw_line(int x1, int x2, int y1, int y2) {
        String point1 = makePoint(x1, y1);
        String point2 = makePoint(x2, y2);

        // order of points should not matter.
        if (point1.compareTo(point2) > 0) {
            String temp = point1;
            point1 = point2;
            point2 = temp;
        }

        String line = String.format("line %s -> %s", point1, point2);
        System.out.println(line);
        drawHistory.add(line);
    }

    private String makePoint(int x, int y) {
        return String.format("(%d,%d)", x, y);
    }

    public void draw_circle(int x, int y, int r) {
        String center = makePoint(x, y);
        String circle = String.format("circle centre %s radius %d", center, r);
        System.out.println(circle);
        drawHistory.add(circle);
    }

    public static Iterator<String> iterator() {
        ArrayList<String> strings = new ArrayList<>(drawHistory);
        Iterator<String> result = strings.iterator();
        drawHistory.clear();
        return result;
    }
}
