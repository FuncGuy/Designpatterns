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

public class IntArrayDeque implements Cloneable {
    // TODO: extend AbstractIntCollection and change references to
    // TODO: IntCollection instead of IntArrayDeque where you can.
    // TODO: toString() is the same as in AbtractIntCollection - delete
    transient Integer[] elements;

    transient int head;

    transient int tail;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int needed) {
        // overflow-conscious code
        final int oldCapacity = elements.length;
        int newCapacity;
        // Double capacity if small; else grow by 50%
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);
        if (jump < needed
            || (newCapacity = (oldCapacity + jump)) - MAX_ARRAY_SIZE > 0)
            newCapacity = newCapacity(needed, jump);
        final Integer[] es = elements = Arrays.copyOf(elements, newCapacity);
        // Exceptionally, here tail == head needs to be disambiguated
        if (tail < head || (tail == head && es[head] != null)) {
            // wrap around; slide first leg forward to end of array
            int newSpace = newCapacity - oldCapacity;
            System.arraycopy(es, head,
                es, head + newSpace,
                oldCapacity - head);
            for (int i = head, to = (head += newSpace); i < to; i++)
                es[i] = null;
        }
    }

    private int newCapacity(int needed, int jump) {
        final int oldCapacity = elements.length, minCapacity;
        if ((minCapacity = oldCapacity + needed) - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0)
                throw new IllegalStateException("Sorry, deque too big");
            return Integer.MAX_VALUE;
        }
        if (needed > jump)
            return minCapacity;
        return (oldCapacity + jump - MAX_ARRAY_SIZE < 0)
            ? oldCapacity + jump
            : MAX_ARRAY_SIZE;
    }

    public IntArrayDeque() {
        elements = new Integer[16];
    }

    public IntArrayDeque(int numElements) {
        elements =
            new Integer[(numElements < 1) ? 1 :
                (numElements == Integer.MAX_VALUE) ? Integer.MAX_VALUE :
                    numElements + 1];
    }

    public IntArrayDeque(IntArrayDeque c) {
        this(c.size());
        addAll(c);
    }

    static final int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    static final int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }

    static final int add(int i, int distance, int modulus) {
        if ((i += distance) - modulus >= 0) i -= modulus;
        return i;
    }

    static final int sub(int i, int j, int modulus) {
        if ((i -= j) < 0) i += modulus;
        return i;
    }

    static final OptionalInt elementAt(Integer[] es, int i) {
        Integer e = es[i];
        return e == null ? OptionalInt.empty() : OptionalInt.of(e);
    }

    static final Integer nonNullElementAt(Integer[] es, int i) {
        Integer e = es[i];
        if (e == null)
            throw new ConcurrentModificationException();
        return e;
    }

    public void addFirst(int e) {
        final Integer[] es = elements;
        es[head = dec(head, es.length)] = e;
        if (head == tail)
            grow(1);
    }

    public void addLast(int e) {
        final Integer[] es = elements;
        es[tail] = e;
        if (head == (tail = inc(tail, es.length)))
            grow(1);
    }

    public boolean addAll(IntArrayDeque c) {
        final int s, needed;
        if ((needed = (s = size()) + c.size() + 1 - elements.length) > 0)
            grow(needed);
        c.forEach(this::addLast);
        return size() > s;
    }

    public boolean offerFirst(int e) {
        addFirst(e);
        return true;
    }

    public boolean offerLast(int e) {
        addLast(e);
        return true;
    }

    public int removeFirst() {
        OptionalInt e = pollFirst();
        return e.orElseThrow(NoSuchElementException::new);
    }

    public int removeLast() {
        OptionalInt e = pollLast();
        return e.orElseThrow(NoSuchElementException::new);
    }

    public OptionalInt pollFirst() {
        final Integer[] es;
        final int h;
        OptionalInt e = elementAt(es = elements, h = head);
        if (e.isPresent()) {
            es[h] = null;
            head = inc(h, es.length);
        }
        return e;
    }

    public OptionalInt pollLast() {
        final Integer[] es;
        final int t;
        OptionalInt e = elementAt(es = elements, t = dec(tail, es.length));
        if (e.isPresent())
            es[tail = t] = null;
        return e;
    }

    public int getFirst() {
        OptionalInt e = elementAt(elements, head);
        return e.orElseThrow(NoSuchElementException::new);
    }

    public int getLast() {
        final Integer[] es = elements;
        OptionalInt e = elementAt(es, dec(tail, es.length));
        return e.orElseThrow(NoSuchElementException::new);
    }

    public OptionalInt peekFirst() {
        return elementAt(elements, head);
    }

    public OptionalInt peekLast() {
        final Integer[] es;
        return elementAt(es = elements, dec(tail, es.length));
    }

    public boolean removeFirstOccurrence(int o) {
        final Integer[] es = elements;
        for (int i = head, end = tail, to = (i <= end) ? end : es.length;
            ; i = 0, to = end) {
            for (; i < to; i++)
                if (o == es[i]) {
                    delete(i);
                    return true;
                }
            if (to == end) break;
        }
        return false;
    }

    public boolean removeLastOccurrence(int o) {
        final Integer[] es = elements;
        for (int i = tail, end = head, to = (i >= end) ? end : 0;
            ; i = es.length, to = end) {
            for (i--; i > to - 1; i--)
                if (o == es[i]) {
                    delete(i);
                    return true;
                }
            if (to == end) break;
        }
        return false;
    }

    public boolean add(int e) {
        addLast(e);
        return true;
    }

    public boolean offer(int e) {
        return offerLast(e);
    }

    public int remove() {
        return removeFirst();
    }

    public OptionalInt poll() {
        return pollFirst();
    }

    public int element() {
        return getFirst();
    }

    public OptionalInt peek() {
        return peekFirst();
    }

    public void push(Integer e) {
        addFirst(e);
    }

    public Integer pop() {
        return removeFirst();
    }

    boolean delete(int i) {
        final Integer[] es = elements;
        final int capacity = es.length;
        final int h, t;
        // number of elements before to-be-deleted elt
        final int front = sub(i, h = head, capacity);
        // number of elements after to-be-deleted elt
        final int back = sub(t = tail, i, capacity) - 1;
        if (front < back) {
            // move front elements forwards
            if (h <= i) {
                System.arraycopy(es, h, es, h + 1, front);
            } else { // Wrap around
                System.arraycopy(es, 0, es, 1, i);
                es[0] = es[capacity - 1];
                System.arraycopy(es, h, es, h + 1, front - (i + 1));
            }
            es[h] = null;
            head = inc(h, capacity);
            return false;
        } else {
            // move back elements backwards
            tail = dec(t, capacity);
            if (i <= tail) {
                System.arraycopy(es, i + 1, es, i, back);
            } else { // Wrap around
                System.arraycopy(es, i + 1, es, i, capacity - (i + 1));
                es[capacity - 1] = es[0];
                System.arraycopy(es, 1, es, 0, t - 1);
            }
            es[tail] = null;
            return true;
        }
    }

    public int size() {
        return sub(tail, head, elements.length);
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public IntIterator iterator() {
        return new DeqIterator();
    }

    public IntIterator descendingIterator() {
        return new DescendingIterator();
    }

    private class DeqIterator implements IntIterator {
        int cursor;

        int remaining = size();

        int lastRet = -1;

        DeqIterator() {
            cursor = head;
        }

        public final boolean hasNext() {
            return remaining > 0;
        }

        public int next() {
            if (remaining <= 0)
                throw new NoSuchElementException();
            final Integer[] es = elements;
            int e = nonNullElementAt(es, cursor);
            cursor = inc(lastRet = cursor, es.length);
            remaining--;
            return e;
        }

        void postDelete(boolean leftShifted) {
            if (leftShifted)
                cursor = dec(cursor, elements.length);
        }

        public final void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            postDelete(delete(lastRet));
            lastRet = -1;
        }

        public void forEachRemaining(IntConsumer action) {
            Objects.requireNonNull(action);
            int r;
            if ((r = remaining) <= 0)
                return;
            remaining = 0;
            final Integer[] es = elements;
            if (es[cursor] == null || sub(tail, cursor, es.length) != r)
                throw new ConcurrentModificationException();
            for (int i = cursor, end = tail, to = (i <= end) ? end : es.length;
                ; i = 0, to = end) {
                for (; i < to; i++)
                    action.accept(elementAt(es, i).orElseThrow(ConcurrentModificationException::new));
                if (to == end) {
                    if (end != tail)
                        throw new ConcurrentModificationException();
                    lastRet = dec(end, es.length);
                    break;
                }
            }
        }
    }

    private class DescendingIterator extends DeqIterator {
        DescendingIterator() {
            cursor = dec(tail, elements.length);
        }

        public final int next() {
            if (remaining <= 0)
                throw new NoSuchElementException();
            final Integer[] es = elements;
            int e = nonNullElementAt(es, cursor);
            cursor = dec(lastRet = cursor, es.length);
            remaining--;
            return e;
        }

        void postDelete(boolean leftShifted) {
            if (!leftShifted)
                cursor = inc(cursor, elements.length);
        }

        public final void forEachRemaining(IntConsumer action) {
            Objects.requireNonNull(action);
            int r;
            if ((r = remaining) <= 0)
                return;
            remaining = 0;
            final Integer[] es = elements;
            if (es[cursor] == null || sub(cursor, head, es.length) + 1 != r)
                throw new ConcurrentModificationException();
            for (int i = cursor, end = head, to = (i >= end) ? end : 0;
                ; i = es.length - 1, to = end) {
                // hotspot generates faster code than for: i >= to !
                for (; i > to - 1; i--)
                    action.accept(elementAt(es, i).orElseThrow(ConcurrentModificationException::new));
                if (to == end) {
                    if (end != head)
                        throw new ConcurrentModificationException();
                    lastRet = end;
                    break;
                }
            }
        }
    }

    public Spliterator.OfInt spliterator() {
        return new DeqSpliterator();
    }

    final class DeqSpliterator implements Spliterator.OfInt {
        private int fence;      // -1 until first use
        private int cursor;     // current index, modified on traverse/split

        DeqSpliterator() {
            this.fence = -1;
        }

        DeqSpliterator(int origin, int fence) {
            // assert 0 <= origin && origin < elements.length;
            // assert 0 <= fence && fence < elements.length;
            this.cursor = origin;
            this.fence = fence;
        }

        private int getFence() { // force initialization
            int t;
            if ((t = fence) < 0) {
                t = fence = tail;
                cursor = head;
            }
            return t;
        }

        public DeqSpliterator trySplit() {
            final Integer[] es = elements;
            final int i, n;
            return ((n = sub(getFence(), i = cursor, es.length) >> 1) <= 0)
                ? null
                : new DeqSpliterator(i, cursor = add(i, n, es.length));
        }

        public void forEachRemaining(IntConsumer action) {
            if (action == null)
                throw new NullPointerException();
            final int end = getFence(), cursor = this.cursor;
            final Integer[] es = elements;
            if (cursor != end) {
                this.cursor = end;
                // null check at both ends of range is sufficient
                if (es[cursor] == null || es[dec(end, es.length)] == null)
                    throw new ConcurrentModificationException();
                for (int i = cursor, to = (i <= end) ? end : es.length;
                    ; i = 0, to = end) {
                    for (; i < to; i++)
                        action.accept(elementAt(es, i).orElseThrow(ConcurrentModificationException::new));
                    if (to == end) break;
                }
            }
        }

        public boolean tryAdvance(IntConsumer action) {
            Objects.requireNonNull(action);
            final Integer[] es = elements;
            if (fence < 0) {
                fence = tail;
                cursor = head;
            } // late-binding
            final int i;
            if ((i = cursor) == fence)
                return false;
            int e = nonNullElementAt(es, i);
            cursor = inc(i, es.length);
            action.accept(e);
            return true;
        }

        public long estimateSize() {
            return sub(getFence(), cursor, elements.length);
        }

        public int characteristics() {
            return Spliterator.NONNULL
                | Spliterator.ORDERED
                | Spliterator.SIZED
                | Spliterator.SUBSIZED;
        }
    }

    public void forEach(IntConsumer action) {
        Objects.requireNonNull(action);
        final Integer[] es = elements;
        for (int i = head, end = tail, to = (i <= end) ? end : es.length;
            ; i = 0, to = end) {
            for (; i < to; i++)
                action.accept(elementAt(es, i).orElseThrow(ConcurrentModificationException::new));
            if (to == end) {
                if (end != tail)
                    throw new ConcurrentModificationException();
                break;
            }
        }
    }

    public boolean removeIf(IntPredicate filter) {
        Objects.requireNonNull(filter);
        return bulkRemove(filter);
    }

    public boolean removeAll(IntArrayDeque c) {
        Objects.requireNonNull(c);
        return bulkRemove(c::contains);
    }

    public boolean retainAll(IntArrayDeque c) {
        Objects.requireNonNull(c);
        return bulkRemove(e -> !c.contains(e));
    }

    private boolean bulkRemove(IntPredicate filter) {
        final Integer[] es = elements;
        // Optimize for initial run of survivors
        for (int i = head, end = tail, to = (i <= end) ? end : es.length;
            ; i = 0, to = end) {
            for (; i < to; i++)
                if (filter.test(elementAt(es, i).orElseThrow(ConcurrentModificationException::new)))
                    return bulkRemoveModified(filter, i);
            if (to == end) {
                if (end != tail)
                    throw new ConcurrentModificationException();
                break;
            }
        }
        return false;
    }

    // A tiny bit set implementation

    private static long[] nBits(int n) {
        return new long[((n - 1) >> 6) + 1];
    }

    private static void setBit(long[] bits, int i) {
        bits[i >> 6] |= 1L << i;
    }

    private static boolean isClear(long[] bits, int i) {
        return (bits[i >> 6] & (1L << i)) == 0;
    }

    private boolean bulkRemoveModified(
        IntPredicate filter, final int beg) {
        final Integer[] es = elements;
        final int capacity = es.length;
        final int end = tail;
        final long[] deathRow = nBits(sub(end, beg, capacity));
        deathRow[0] = 1L;   // set bit 0
        for (int i = beg + 1, to = (i <= end) ? end : es.length, k = beg;
            ; i = 0, to = end, k -= capacity) {
            for (; i < to; i++)
                if (filter.test(elementAt(es, i).orElseThrow(ConcurrentModificationException::new)))
                    setBit(deathRow, i - k);
            if (to == end) break;
        }
        // a two-finger traversal, with hare i reading, tortoise w writing
        int w = beg;
        for (int i = beg + 1, to = (i <= end) ? end : es.length, k = beg;
            ; w = 0) { // w rejoins i on second leg
            // In this loop, i and w are on the same leg, with i > w
            for (; i < to; i++)
                if (isClear(deathRow, i - k))
                    es[w++] = es[i];
            if (to == end) break;
            // In this loop, w is on the first leg, i on the second
            for (i = 0, to = end, k -= capacity; i < to && w < capacity; i++)
                if (isClear(deathRow, i - k))
                    es[w++] = es[i];
            if (i >= to) {
                if (w == capacity) w = 0; // "corner" case
                break;
            }
        }
        if (end != tail)
            throw new ConcurrentModificationException();
        circularClear(es, tail = w, end);
        return true;
    }

    public boolean contains(int o) {
        final Integer[] es = elements;
        for (int i = head, end = tail, to = (i <= end) ? end : es.length;
            ; i = 0, to = end) {
            for (; i < to; i++)
                if (o == es[i])
                    return true;
            if (to == end) break;
        }
        return false;
    }

    public boolean remove(int element) {
        return removeFirstOccurrence(element);
    }

    public void clear() {
        circularClear(elements, head, tail);
        head = tail = 0;
    }

    private static void circularClear(Integer[] es, int i, int end) {
        // assert 0 <= i && i < es.length;
        // assert 0 <= end && end < es.length;
        for (int to = (i <= end) ? end : es.length;
            ; i = 0, to = end) {
            for (; i < to; i++) es[i] = null;
            if (to == end) break;
        }
    }

    public int[] toArray() {
        return stream().toArray();
    }

    // *** Object methods ***

    public IntArrayDeque clone() {
        try {
            IntArrayDeque result = (IntArrayDeque) super.clone();
            result.elements = Arrays.copyOf(elements, elements.length);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    void checkInvariants() {
        // Use head and tail fields with empty slot at tail strategy.
        // head == tail disambiguates to "empty".
        try {
            // int capacity = elements.length;
            // assert 0 <= head && head < capacity;
            // assert 0 <= tail && tail < capacity;
            // assert capacity > 0;
            // assert size() < capacity;
            // assert head == tail || elements[head] != null;
            // assert elements[tail] == null;
            // assert head == tail || elements[dec(tail, capacity)] != null;
        } catch (Throwable t) {
            System.err.printf("head=%d tail=%d capacity=%d%n",
                head, tail, elements.length);
            System.err.printf("elements=%s%n",
                Arrays.toString(elements));
            throw t;
        }
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


    // Bulk Operations

    public boolean containsAll(IntArrayDeque c) {
        for (IntIterator it = this.iterator(); it.hasNext(); ) {
            if (!contains(it.next()))
                return false;
        }
        return true;
    }

}
