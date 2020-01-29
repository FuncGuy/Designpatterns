/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package bridge.exercise1;

import org.junit.*;

import java.lang.reflect.*;
import java.util.*;

import static org.junit.Assert.*;
import static util.TestHelpers.*;

//DON'T CHANGE
public class BridgeTest {
    @Test
    public void testConcreteShapesDoNotContainDrawingFields() {
        checkClassForDrawingField(Circle.class);
        checkClassForDrawingField(Rectangle.class);
        checkClassForDrawingField(Triangle.class);
    }

    private void checkClassForDrawingField(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType() == Drawing.class) {
                fail("Concrete shapes should not contain references to Drawing");
            }
        }
        checkParents(clazz.getSuperclass());
    }

    private void checkParents(Class<?> clazz) {
        if (clazz == null) return;
        for (Field field : Shape.class.getDeclaredFields()) {
            if (field.getType() == Drawing.class) {
                assertTrue("Drawing field should be private", Modifier.isPrivate(field.getModifiers()));
            }
        }
        checkParents(clazz.getSuperclass());
    }

    @Test
    public void testShapeDoesNotExposeDrawingField() {
        for (Method method : Shape.class.getDeclaredMethods()) {
            if (method.getReturnType() == Drawing.class) {
                fail("Shape should not expose Drawing class via a method either");
            }
        }
    }

    @Test
    public void testBridgeMethods() throws Exception {
        Collection<Class<? extends Drawing>> classes = getDrawingClasses();
        assertEquals("We would expect two Drawing implementations", 2, classes.size());

        outer:
        for (Class<? extends Drawing> clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == DrawingTool1.class) {
                    testWithDrawingTool1(clazz.getConstructor().newInstance());
                    continue outer;
                } else if (field.getType() == DrawingTool2.class) {
                    testWithDrawingTool2(clazz.getConstructor().newInstance());
                    continue outer;
                }
            }
            if (clazz.getName().contains("1")) {
                testWithDrawingTool1(clazz.getConstructor().newInstance());
                continue outer;
            } else if (clazz.getName().contains("2")) {
                testWithDrawingTool2(clazz.getConstructor().newInstance());
                continue outer;
            }
            fail("Could not figure out which Drawing classes are for which DrawingTools - please help me!");
        }
    }

    private void testWithDrawingTool1(Drawing drawing) throws Exception {
        draw(new Rectangle(drawing, 0, 0, 100, 200));
        compareResults(DrawingTool1.iterator(),
            "line (0,0) -> (0,200)",
            "line (0,0) -> (100,0)",
            "line (0,200) -> (100,200)",
            "line (100,0) -> (100,200)"
        );

        draw(new Triangle(drawing, 0, 0, 100, 200, 300, 400));
        compareResults(DrawingTool1.iterator(),
            "line (0,0) -> (100,200)",
            "line (0,0) -> (300,400)",
            "line (100,200) -> (300,400)");

        draw(new Circle(drawing, 100, 100, 40));
        compareResults(DrawingTool1.iterator(),
            "circle centre (100,100) radius 40");
    }

    private void draw(Object shape) throws Exception {
        Method draw = shape.getClass().getMethod("draw");
        draw.invoke(shape);
    }

    private void compareResults(Iterator<String> actualLines, String... expectedOutput) {
        for (String expectedLine : expectedOutput) {
            assertEquals(expectedLine, actualLines.next());
        }
        assertFalse(actualLines.hasNext());
    }

    private void testWithDrawingTool2(Drawing drawing) throws Exception {
        draw(new Rectangle(drawing, 40, 50, 300, 500));
        compareResults(DrawingTool2.iterator(),
            "line java.awt.Point[x=300,y=500] -> java.awt.Point[x=300,y=50]",
            "line java.awt.Point[x=300,y=500] -> java.awt.Point[x=40,y=500]",
            "line java.awt.Point[x=300,y=50] -> java.awt.Point[x=40,y=50]",
            "line java.awt.Point[x=40,y=500] -> java.awt.Point[x=40,y=50]");

        draw(new Triangle(drawing, 0, 0, 100, 200, 300, 400));
        compareResults(DrawingTool2.iterator(),
            "line java.awt.Point[x=0,y=0] -> java.awt.Point[x=100,y=200]",
            "line java.awt.Point[x=0,y=0] -> java.awt.Point[x=300,y=400]",
            "line java.awt.Point[x=100,y=200] -> java.awt.Point[x=300,y=400]");

        draw(new Circle(drawing, 100, 100, 40));
        compareResults(DrawingTool2.iterator(),
            "circle centre java.awt.Point[x=100,y=100] radius 40");
    }

    @Test
    public void testDrawingStructures() throws Exception {
        Collection<Class<? extends Drawing>> classes = getDrawingClasses();
        assertEquals("We would expect two Drawing implementations", 2, classes.size());
        for (Class<? extends Drawing> clazz : classes) {
            assertNotSame("DrawingTool1 should be adapted to the Drawing", DrawingTool1.class, clazz);
            assertNotSame("DrawingTool2 should be adapted to the Drawing", DrawingTool2.class, clazz);
            assertEquals("The Drawing classes should only implement the Drawing interface and not extends any classes", Object.class, clazz.getSuperclass());
        }
    }

    private Collection<Class<? extends Drawing>> getDrawingClasses() throws ClassNotFoundException {
        return getClassesExtending(Drawing.class);
    }

    @Test
    public void testDrawingTools() {
        assertEquals("DrawingTool1 should not implement any interfaces", 0, DrawingTool1.class.getInterfaces().length);
        assertEquals("DrawingTool2 should not implement any interfaces", 0, DrawingTool2.class.getInterfaces().length);
    }
}
