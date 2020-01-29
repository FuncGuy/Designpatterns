/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package bridge.solution1;

public class DrawingImpl1 implements Drawing {
    public void drawLine(int x1, int y1, int x2, int y2) {
        new DrawingTool1().draw_line(x1, x2, y1, y2);
    }

    public void drawCircle(int x, int y, int r) {
        new DrawingTool1().draw_circle(x, y, r);
    }
}
