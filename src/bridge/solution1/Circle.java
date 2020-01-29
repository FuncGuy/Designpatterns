/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package bridge.solution1;

public class Circle extends Shape {
    private final int x;
    private final int y;
    private final int r;

    public Circle(Drawing drawing,
                  int x, int y, int r) {
        super(drawing);
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void draw() {
        drawCircle(x, y, r);
    }

}
