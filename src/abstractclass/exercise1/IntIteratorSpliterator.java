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

// DON'T CHANGE
public class IntIteratorSpliterator implements Spliterator.OfInt {
    static final int BATCH_UNIT = 1 << 10;  // batch array size increment
    static final int MAX_BATCH = 1 << 25;  // max batch array size;
    private final IntCollection collection; // null OK
    private IntIterator it;
    private final int characteristics;
    private long est;             // size estimate
    private int batch;            // batch size for splits

    public IntIteratorSpliterator(IntCollection collection, int characteristics) {
        this.collection = collection;
        this.it = null;
        this.characteristics = (characteristics & Spliterator.CONCURRENT) == 0
            ? characteristics | Spliterator.SIZED | Spliterator.SUBSIZED
            : characteristics;
    }

    public IntIteratorSpliterator(IntIterator iterator, long size, int characteristics) {
        this.collection = null;
        this.it = iterator;
        this.est = size;
        this.characteristics = (characteristics & Spliterator.CONCURRENT) == 0
            ? characteristics | Spliterator.SIZED | Spliterator.SUBSIZED
            : characteristics;
    }

    public IntIteratorSpliterator(IntIterator iterator, int characteristics) {
        this.collection = null;
        this.it = iterator;
        this.est = Long.MAX_VALUE;
        this.characteristics = characteristics & ~(Spliterator.SIZED | Spliterator.SUBSIZED);
    }

    @Override
    public Spliterator.OfInt trySplit() {
        IntIterator i;
        long s;
        if ((i = it) == null) {
            i = it = collection.iterator();
            s = est = (long) collection.size();
        } else
            s = est;
        if (s > 1 && i.hasNext()) {
            int n = batch + BATCH_UNIT;
            if (n > s)
                n = (int) s;
            if (n > MAX_BATCH)
                n = MAX_BATCH;
            int[] a = new int[n];
            int j = 0;
            do { a[j] = i.next(); }
            while (++j < n && i.hasNext());
            batch = j;
            if (est != Long.MAX_VALUE)
                est -= j;
            return new IntArraySpliterator(a, 0, j, characteristics);
        }
        return null;
    }

    @Override
    public void forEachRemaining(IntConsumer action) {
        if (action == null) throw new NullPointerException();
        IntIterator i;
        if ((i = it) == null) {
            i = it = collection.iterator();
            est = (long) collection.size();
        }
        i.forEachRemaining(action);
    }

    @Override
    public boolean tryAdvance(IntConsumer action) {
        if (action == null) throw new NullPointerException();
        if (it == null) {
            it = collection.iterator();
            est = (long) collection.size();
        }
        if (it.hasNext()) {
            action.accept(it.next());
            return true;
        }
        return false;
    }

    @Override
    public long estimateSize() {
        if (it == null) {
            it = collection.iterator();
            return est = (long) collection.size();
        }
        return est;
    }

    @Override
    public int characteristics() {
        return characteristics;
    }


    static final class IntArraySpliterator implements Spliterator.OfInt {
        private final int[] array;
        private int index;        // current index, modified on advance/split
        private final int fence;  // one past last index
        private final int characteristics;

        public IntArraySpliterator(int[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        public IntArraySpliterator(int[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public OfInt trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid)
                ? null
                : new IntArraySpliterator(array, lo, index = mid, characteristics);
        }

        @Override
        public void forEachRemaining(IntConsumer action) {
            int[] a;
            int i, hi; // hoist accesses and checks from loop
            if (action == null)
                throw new NullPointerException();
            if ((a = array).length >= (hi = fence) &&
                (i = index) >= 0 && i < (index = hi)) {
                do { action.accept(a[i]); } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            if (action == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            return (long) (fence - index);
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super Integer> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED))
                return null;
            throw new IllegalStateException();
        }
    }

}
