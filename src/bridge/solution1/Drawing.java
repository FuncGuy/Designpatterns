/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package bridge.solution1;

//DON'T CHANGE
public interface Drawing {
    void drawLine(int x1, int y1, int x2, int y2);

    void drawCircle(int x, int y, int r);
}
