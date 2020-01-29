/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractclass.exercise1;

import org.junit.*;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class AbstractClassTest {
    public static class ReadOnlyIntArrayList extends AbstractIntCollection {
        private final int[] elements;

        public ReadOnlyIntArrayList(int... elements) {
            this.elements = elements.clone();
        }

        public IntIterator iterator() {
            return new IntIterator() {
                private int pos = 0;

                public boolean hasNext() {
                    return pos < size();
                }

                public int next() {
                    if (!hasNext())
                        throw new NoSuchElementException();
                    return elements[pos++];
                }
            };
        }

        public int size() {
            return elements.length;
        }
    }


    @Test
    public void testContains() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method contains = IntCollection.class.getMethod("contains", int.class);
        assertTrue((Boolean) contains.invoke(new ReadOnlyIntArrayList(1, 2, 3, 4, 5), 3));
    }

    @Test
    public void testContainsAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method containsAll = IntCollection.class.getMethod("containsAll", IntCollection.class);
        assertTrue((Boolean) containsAll.invoke(new ReadOnlyIntArrayList(1, 2, 3, 4, 5),
            new ReadOnlyIntArrayList(2, 4, 5)));
    }

    @Test
    public void testToString() {
        assertEquals("[1, 2, 3, 4, 5]", new ReadOnlyIntArrayList(1, 2, 3, 4, 5).toString());
    }

    @Test
    public void testToArray() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method toArray = IntCollection.class.getMethod("toArray");
        int[] ints = {1, 2, 3, 4, 5};
        IntArrayList ial = new IntArrayList();
        for (int i : ints) {
            ial.add(i);
        }
        assertArrayEquals(ints, (int[]) toArray.invoke(ial));
        IntArrayDeque iad = new IntArrayDeque();
        for (int i : ints) {
            iad.add(i);
        }
        assertArrayEquals(ints, (int[]) toArray.invoke(iad));
    }

    @Test
    public void testSize() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method size = IntCollection.class.getMethod("size");
        int[] ints = {1, 2, 3, 4, 5};
        IntArrayList ial = new IntArrayList();
        for (int i : ints) {
            ial.add(i);
        }
        assertEquals(5, ((Integer) size.invoke(ial)).intValue());
        IntArrayDeque iad = new IntArrayDeque();
        for (int i : ints) {
            iad.add(i);
        }
        assertEquals(5, ((Integer) size.invoke(iad)).intValue());
    }

    @Test
    public void testIsEmpty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method isEmpty = IntCollection.class.getMethod("isEmpty");
        int[] ints = {1, 2, 3, 4, 5};
        IntArrayList ial = new IntArrayList();
        assertTrue((Boolean) isEmpty.invoke(ial));
        for (int i : ints) {
            ial.add(i);
        }
        assertFalse((Boolean) isEmpty.invoke(ial));
        IntArrayDeque iad = new IntArrayDeque();
        assertTrue((Boolean) isEmpty.invoke(iad));
        for (int i : ints) {
            iad.add(i);
        }
        assertFalse((Boolean) isEmpty.invoke(iad));
    }

    @Test
    public void testAdd() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method add = IntCollection.class.getMethod("add", int.class);
        Method toArray = IntCollection.class.getMethod("toArray");
        int[] ints = {1, 2, 3, 4, 5};
        IntArrayList ial = new IntArrayList();
        IntArrayDeque iad = new IntArrayDeque();
        for (int i : ints) {
            add.invoke(ial, i);
            add.invoke(iad, i);
        }
        assertArrayEquals((int[]) toArray.invoke(ial), (int[]) toArray.invoke(iad));
    }

    @Test
    public void testRemove() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method remove = IntCollection.class.getMethod("remove", int.class);
        Method add = IntCollection.class.getMethod("add", int.class);
        int[] ints = {1, 2, 3, 4, 5};
        IntArrayList ial = new IntArrayList();
        IntArrayDeque iad = new IntArrayDeque();
        for (int i : ints) {
            assertTrue((Boolean) add.invoke(ial, i));
            assertTrue((Boolean) add.invoke(iad, i));
        }
        for (int i : ints) {
            assertTrue((Boolean) remove.invoke(ial, i));
            assertTrue((Boolean) remove.invoke(iad, i));
        }

        assertFalse((Boolean) remove.invoke(ial, 0));
        assertFalse((Boolean) remove.invoke(iad, 0));
    }

    @Test
    public void testAddAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method addAllCollection = IntCollection.class.getMethod("addAll", IntCollection.class);
        Method addAllArrayList = IntArrayList.class.getMethod("addAll", IntCollection.class);
        Method addAllArrayDeque = IntArrayDeque.class.getMethod("addAll", IntCollection.class);
        IntCollection toAdd = new ReadOnlyIntArrayList(1, 2, 3, 4, 5);

        IntArrayList ial = new IntArrayList();
        IntArrayDeque iad = new IntArrayDeque();
        assertTrue((Boolean) addAllCollection.invoke(ial, toAdd));
        assertTrue((Boolean) addAllArrayList.invoke(ial, toAdd));
        assertTrue((Boolean) addAllCollection.invoke(iad, toAdd));
        assertTrue((Boolean) addAllArrayDeque.invoke(iad, toAdd));

        assertEquals(10, ial.size());
        assertEquals(10, iad.size());

        assertEquals("[1, 2, 3, 4, 5, 1, 2, 3, 4, 5]", ial.toString());
        assertEquals("[1, 2, 3, 4, 5, 1, 2, 3, 4, 5]", iad.toString());
    }

    @Test
    public void testRemoveAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method removeAllCollection = IntCollection.class.getMethod("removeAll", IntCollection.class);
        Method removeAllArrayList = IntArrayList.class.getMethod("removeAll", IntCollection.class);
        Method removeAllArrayDeque = IntArrayDeque.class.getMethod("removeAll", IntCollection.class);
        IntArrayList ial = new IntArrayList();
        IntArrayDeque iad = new IntArrayDeque();
        for (int i = 1; i <= 10; i++) {
            ial.add(i);
            iad.add(i);
        }
        IntCollection toRemove1 = new ReadOnlyIntArrayList(1, 2, 3);
        IntCollection toRemove2 = new ReadOnlyIntArrayList(6, 7, 9);

        assertTrue((Boolean) removeAllCollection.invoke(ial, toRemove1));
        assertEquals("[4, 5, 6, 7, 8, 9, 10]", ial.toString());
        assertTrue((Boolean) removeAllArrayList.invoke(ial, toRemove2));
        assertEquals("[4, 5, 8, 10]", ial.toString());
        assertTrue((Boolean) removeAllCollection.invoke(iad, toRemove1));
        assertEquals("[4, 5, 6, 7, 8, 9, 10]", iad.toString());
        assertTrue((Boolean) removeAllArrayDeque.invoke(iad, toRemove2));
        assertEquals("[4, 5, 8, 10]", iad.toString());
    }

    @Test
    public void testRemoveIf() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method removeAllCollection = IntCollection.class.getMethod("removeIf", IntPredicate.class);
        Method removeAllArrayList = IntArrayList.class.getMethod("removeIf", IntPredicate.class);
        Method removeAllArrayDeque = IntArrayDeque.class.getMethod("removeIf", IntPredicate.class);
        IntArrayList ial = new IntArrayList();
        IntArrayDeque iad = new IntArrayDeque();
        for (int i = 1; i <= 10; i++) {
            ial.add(i);
            iad.add(i);
        }

        IntPredicate odd = value -> value % 2 == 1;
        IntPredicate even = value -> value % 2 == 0;
        assertTrue((Boolean) removeAllCollection.invoke(ial, odd));
        assertEquals("[2, 4, 6, 8, 10]", ial.toString());
        assertTrue((Boolean) removeAllArrayList.invoke(ial, even));
        assertEquals("[]", ial.toString());
        assertTrue((Boolean) removeAllCollection.invoke(iad, odd));
        assertEquals("[2, 4, 6, 8, 10]", iad.toString());
        assertTrue((Boolean) removeAllArrayDeque.invoke(iad, even));
        assertEquals("[]", iad.toString());
    }

    @Test
    public void testRetainAll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method retainAllCollection = IntCollection.class.getMethod("retainAll", IntCollection.class);
        Method retainAllArrayList = IntArrayList.class.getMethod("retainAll", IntCollection.class);
        Method retainAllArrayDeque = IntArrayDeque.class.getMethod("retainAll", IntCollection.class);
        IntArrayList ial = new IntArrayList();
        IntArrayDeque iad = new IntArrayDeque();
        for (int i = 1; i <= 10; i++) {
            ial.add(i);
            iad.add(i);
        }
        IntCollection toRetain1 = new ReadOnlyIntArrayList(1, 2, 3, 6, 9, 10, 99, -100);
        IntCollection toRetain2 = new ReadOnlyIntArrayList(6, 7, 9);

        assertTrue((Boolean) retainAllCollection.invoke(ial, toRetain1));
        assertEquals("[1, 2, 3, 6, 9, 10]", ial.toString());
        assertTrue((Boolean) retainAllArrayList.invoke(ial, toRetain2));
        assertEquals("[6, 9]", ial.toString());
        assertTrue((Boolean) retainAllCollection.invoke(iad, toRetain1));
        assertEquals("[1, 2, 3, 6, 9, 10]", iad.toString());
        assertTrue((Boolean) retainAllArrayDeque.invoke(iad, toRetain2));
        assertEquals("[6, 9]", iad.toString());
    }

    @Test
    public void testClear() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method clearCollection = IntCollection.class.getMethod("clear");
        IntArrayList ial = new IntArrayList();
        IntArrayDeque iad = new IntArrayDeque();
        for (int i = 1; i <= 10; i++) {
            ial.add(i);
            iad.add(i);
        }
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", ial.toString());
        clearCollection.invoke(ial);
        assertEquals("[]", ial.toString());

        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", iad.toString());
        clearCollection.invoke(iad);
        assertEquals("[]", iad.toString());
    }

    @Test
    public void testStream() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method streamCollection = IntCollection.class.getMethod("stream");
        IntArrayList ial = new IntArrayList();
        IntArrayDeque iad = new IntArrayDeque();
        for (int i = 1; i <= 10; i++) {
            ial.add(i);
            iad.add(i);
        }

        IntStream ialStream = (IntStream) streamCollection.invoke(ial);
        assertEquals(55, ialStream.sum());

        IntStream iadStream = (IntStream) streamCollection.invoke(iad);
        assertEquals(55, iadStream.sum());

    }

    @Test
    public void testMethodsInConcreteClasses() {
        checkClassDoesNotContainMethod(IntArrayList.class, "toString");
        checkClassDoesNotContainMethod(IntArrayList.class, "stream");
        checkClassDoesNotContainMethod(IntArrayList.class, "parallelStream");
        checkClassDoesNotContainMethod(IntArrayDeque.class, "toString");
        checkClassDoesNotContainMethod(IntArrayDeque.class, "stream");
        checkClassDoesNotContainMethod(IntArrayDeque.class, "parallelStream");
    }

    private void checkClassDoesNotContainMethod(Class<?> clazz, String method) {
        try {
            clazz.getDeclaredMethod(method);
            fail(method + "() should be common in AbstractIntCollection and not declared in " + clazz.getSimpleName());
        } catch (NoSuchMethodException ignored) {
        }
    }
}
