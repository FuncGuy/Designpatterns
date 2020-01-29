/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package bridge.exercise1;

import java.awt.*;
import java.util.*;

//DON'T CHANGE
public class DrawingTool2 {
    /**
     * Used to check that all the drawings were done correctly.
     */
    private static final Collection<String> drawHistory =
        new TreeSet<>();

    public void drawLine(Point origin, Point destination) {
        String point1 = makePoint(origin);
        String point2 = makePoint(destination);

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

    private String makePoint(Point point) {
        return point.toString();
    }

    public void drawCircle(Point centre, int radius) {
        String center = makePoint(centre);
        String circle = String.format("circle centre %s radius %d", center, radius);
        System.out.println(circle);
        drawHistory.add(circle);
    }

    public static Iterator<String> iterator() {
        ArrayList<String> strings = new ArrayList<>(drawHistory);
        Iterator<String> result = strings.iterator();
        System.out.println("drawHistory = " + drawHistory);
        System.out.println("strings = " + strings);
        drawHistory.clear();
        return result;
    }
}
