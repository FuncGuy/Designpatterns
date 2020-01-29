/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package abstractclass.exercise1;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class IntArrayList implements Cloneable {
    // TODO: extend AbstractIntCollection and change references to
    // TODO: IntCollection instead of IntArrayList where you can
    // TODO: toString() is the same as in AbtractIntCollection - delete
    private static final int DEFAULT_CAPACITY = 10;

    private static final int[] EMPTY_ELEMENTDATA = {};

    private static final int[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient int[] elementData;

    private int size;

    public IntArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new int[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                initialCapacity);
        }
    }

    public IntArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public IntArrayList(IntArrayList c) {
        elementData = c.toArray();
        if ((size = elementData.length) == 0) {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                ? EMPTY_ELEMENTDATA
                : Arrays.copyOf(elementData, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length
            && !(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
            && minCapacity <= DEFAULT_CAPACITY)) {
            modCount++;
            grow(minCapacity);
        }
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private int[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
            newCapacity(minCapacity));
    }

    private int[] grow() {
        return grow(size + 1);
    }

    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
            ? newCapacity
            : hugeCapacity(minCapacity);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(int o) {
        for (int i = 0; i < size; i++)
            if (o == elementData[i])
                return i;
        return -1;
    }

    public int lastIndexOf(int o) {
        for (int i = size - 1; i >= 0; i--)
            if (o == elementData[i])
                return i;
        return -1;
    }

    public Object clone() {
        try {
            IntArrayList v = (IntArrayList) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    public int[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    int elementData(int index) {
        return elementData[index];
    }

    static int elementAt(int[] es, int index) {
        return es[index];
    }

    public int get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    public int set(int index, int element) {
        Objects.checkIndex(index, size);
        int oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    private void add(int e, int[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    public boolean add(int e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }

    public void add(int index, int element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        int[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
            elementData, index + 1,
            s - index);
        elementData[index] = element;
        size = s + 1;
    }

    public int removeAt(int index) {
        Objects.checkIndex(index, size);

        modCount++;
        int oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                numMoved);
        elementData[--size] = 0;

        return oldValue;
    }

    public boolean remove(int o) {
        for (int index = 0; index < size; index++)
            if (o == elementData[index]) {
                fastRemove(index);
                return true;
            }
        return false;
    }

    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                numMoved);
        elementData[--size] = 0;
    }

    public void clear() {
        modCount++;
        final int[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = 0;
    }

    public boolean addAll(IntArrayList c) {
        int[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0)
            return false;
        int[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = grow(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    public boolean addAll(int index, IntArrayList c) {
        rangeCheckForAdd(index);

        int[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0)
            return false;
        int[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = grow(s + numNew);

        int numMoved = s - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index,
                elementData, index + numNew,
                numMoved);
        System.arraycopy(a, 0, elementData, index, numNew);
        size = s + numNew;
        return true;
    }

    protected void removeRange(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException(
                outOfBoundsMsg(fromIndex, toIndex));
        }
        modCount++;
        shiftTailOverGap(elementData, fromIndex, toIndex);
    }

    private void shiftTailOverGap(int[] es, int lo, int hi) {
        System.arraycopy(es, hi, es, lo, size - hi);
        for (int to = size, i = (size -= hi - lo); i < to; i++)
            es[i] = 0;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private static String outOfBoundsMsg(int fromIndex, int toIndex) {
        return "From Index: " + fromIndex + " > To Index: " + toIndex;
    }

    public boolean removeAll(IntArrayList c) {
        return batchRemove(c, false, 0, size);
    }

    public boolean retainAll(IntArrayList c) {
        return batchRemove(c, true, 0, size);
    }

    boolean batchRemove(IntArrayList c, boolean complement,
                        final int from, final int end) {
        Objects.requireNonNull(c);
        final int[] es = elementData;
        final boolean modified;
        int r;
        // Optimize for initial run of survivors
        for (r = from; r < end && c.contains(es[r]) == complement; r++)
            ;
        if (modified = (r < end)) {
            int w = r++;
            try {
                for (int e; r < end; r++)
                    if (c.contains(e = es[r]) == complement)
                        es[w++] = e;
            } catch (Throwable ex) {
                // Preserve behavioral compatibility with AbstractCollection,
                // even if c.contains() throws.
                System.arraycopy(es, r, es, w, end - r);
                w += end - r;
                throw ex;
            } finally {
                modCount += end - w;
                shiftTailOverGap(es, w, end);
            }
        }
        return modified;
    }

    public IntListIterator listIterator(int index) {
        rangeCheckForAdd(index);
        return new ListItr(index);
    }

    public IntListIterator listIterator() {
        return new ListItr(0);
    }

    public IntIterator iterator() {
        return new Itr();
    }

    private class Itr implements IntIterator {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        public int next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            int[] elementData = IntArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                IntArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void forEachRemaining(IntConsumer action) {
            Objects.requireNonNull(action);
            final int size = IntArrayList.this.size;
            int i = cursor;
            if (i < size) {
                final int[] es = elementData;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && modCount == expectedModCount; i++)
                    action.accept(elementAt(es, i));
                // update once at end to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class ListItr extends Itr implements IntListIterator {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public int previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            int[] elementData = IntArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return elementData[lastRet = i];
        }

        public void set(int e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                IntArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(int e) {
            checkForComodification();

            try {
                int i = cursor;
                IntArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public void forEach(IntConsumer action) {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        final int[] es = elementData;
        final int size = this.size;
        for (int i = 0; modCount == expectedModCount && i < size; i++)
            action.accept(elementAt(es, i));
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }

    public Spliterator.OfInt spliterator() {
        return new ArrayListSpliterator(0, -1, 0);
    }

    final class ArrayListSpliterator implements Spliterator.OfInt {
        private int index; // current index, modified on advance/split
        private int fence; // -1 until used; then one past last index
        private int expectedModCount; // initialized when fence set

        ArrayListSpliterator(int origin, int fence, int expectedModCount) {
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence() { // initialize fence to size on first use
            int hi; // (a specialized variant appears in method forEach)
            if ((hi = fence) < 0) {
                expectedModCount = modCount;
                hi = fence = size;
            }
            return hi;
        }

        public ArrayListSpliterator trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : // divide range in half unless too small
                new ArrayListSpliterator(lo, index = mid, expectedModCount);
        }

        public boolean tryAdvance(IntConsumer action) {
            if (action == null)
                throw new NullPointerException();
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                action.accept(elementData[i]);
                if (modCount != expectedModCount)
                    throw new ConcurrentModificationException();
                return true;
            }
            return false;
        }

        public void forEachRemaining(IntConsumer action) {
            int i, hi, mc; // hoist accesses and checks from loop
            int[] a;
            if (action == null)
                throw new NullPointerException();
            if ((a = elementData) != null) {
                if ((hi = fence) < 0) {
                    mc = modCount;
                    hi = size;
                } else
                    mc = expectedModCount;
                if ((i = index) >= 0 && (index = hi) <= a.length) {
                    for (; i < hi; ++i) {
                        action.accept(a[i]);
                    }
                    if (modCount == mc)
                        return;
                }
            }
            throw new ConcurrentModificationException();
        }

        public long estimateSize() {
            return getFence() - index;
        }

        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    }

    private static long[] nBits(int n) {
        return new long[((n - 1) >> 6) + 1];
    }

    private static void setBit(long[] bits, int i) {
        bits[i >> 6] |= 1L << i;
    }

    private static boolean isClear(long[] bits, int i) {
        return (bits[i >> 6] & (1L << i)) == 0;
    }

    public boolean removeIf(IntPredicate filter) {
        return removeIf(filter, 0, size);
    }

    boolean removeIf(IntPredicate filter, int i, final int end) {
        Objects.requireNonNull(filter);
        int expectedModCount = modCount;
        final int[] es = elementData;
        // Optimize for initial run of survivors
        for (; i < end && !filter.test(elementAt(es, i)); i++)
            ;
        // Tolerate predicates that reentrantly access the collection for
        // read (but writers still get CME), so traverse once to find
        // elements to delete, a second pass to physically expunge.
        if (i < end) {
            final int beg = i;
            final long[] deathRow = nBits(end - beg);
            deathRow[0] = 1L;   // set bit 0
            for (i = beg + 1; i < end; i++)
                if (filter.test(elementAt(es, i)))
                    setBit(deathRow, i - beg);
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            expectedModCount++;
            modCount++;
            int w = beg;
            for (i = beg; i < end; i++)
                if (isClear(deathRow, i - beg))
                    es[w++] = es[i];
            shiftTailOverGap(es, w, end);
            return true;
        } else {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            return false;
        }
    }

    public void replaceAll(IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        final int expectedModCount = modCount;
        final int[] es = elementData;
        final int size = this.size;
        for (int i = 0; modCount == expectedModCount && i < size; i++)
            es[i] = operator.applyAsInt(elementAt(es, i));
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
        modCount++;
    }

    void checkInvariants() {
        // assert size >= 0;
        // assert size == elementData.length || elementData[size] == null;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        IntListIterator e1 = listIterator();
        IntListIterator e2 = ((IntArrayList) o).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            if (e1.next() != e2.next()) return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    public int hashCode() {
        int hashCode = 1;
        for (IntIterator it = iterator(); it.hasNext(); ) {
            hashCode = 31 * hashCode + it.next();
        }
        return hashCode;
    }

    protected transient int modCount = 0;


    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError
                ("Required array size too large");
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

    public boolean containsAll(IntArrayList c) {
        for (IntIterator it = c.iterator(); it.hasNext(); ) {
            int e = it.next();
            if (!contains(e))
                return false;
        }
        return true;
    }

    public String toString() {
        return stream().mapToObj(Integer::toString)
            .collect(Collectors.joining(", ", "[", "]"));
    }

    public IntStream stream() {
        return StreamSupport.intStream(spliterator(), false);
    }

    public IntStream parallelStream() {
        return StreamSupport.intStream(spliterator(), true);
    }
}
