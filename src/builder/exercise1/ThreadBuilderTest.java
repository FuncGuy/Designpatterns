/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package builder.exercise1;

import org.junit.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class ThreadBuilderTest {
    @Test
    public void testPlainThread() throws InterruptedException {
        AtomicBoolean ran = new AtomicBoolean(false);
        Thread test = new ThreadBuilder(
            () -> ran.set(true), "testPlainThread()")
            .build();
        test.start();
        test.join();
        assertTrue(ran.get());
        assertEquals("testPlainThread()", test.getName());
        assertFalse(test.isDaemon());
        assertEquals(Thread.NORM_PRIORITY, test.getPriority());
    }

    @Test
    public void testThreadGroup() throws InterruptedException {
        ThreadGroup group = new ThreadGroup("testGroup");
        AtomicBoolean ran = new AtomicBoolean(false);
        Thread test = new ThreadBuilder(
            () -> ran.set(true), "testThreadGroup()")
            .threadGroup(group)
            .stackSize(1000)
            .inheritThreadLocals(true)
            .build();
        test.start();
        assertEquals(group, test.getThreadGroup());
        test.join();
        assertTrue(ran.get());
        assertEquals("testThreadGroup()", test.getName());
        assertFalse(test.isDaemon());
        assertEquals(Thread.NORM_PRIORITY, test.getPriority());
    }

    @Test
    public void testDaemonTrue() throws InterruptedException {
        AtomicBoolean ran = new AtomicBoolean(false);
        Thread test = new ThreadBuilder(
            () -> ran.set(true), "testDaemonTrue()")
            .daemon(true)
            .build();
        test.start();
        assertTrue(test.isDaemon());
        test.join();
        assertTrue(ran.get());
        assertEquals("testDaemonTrue()", test.getName());
    }

    @Test
    public void testDaemonChildFalse() throws InterruptedException {
        AtomicBoolean childDaemon = new AtomicBoolean(false);
        Thread test = new ThreadBuilder(
            () -> {
                Thread child =
                    new ThreadBuilder(() -> childDaemon.set(Thread.currentThread().isDaemon()), "testDaemonChildFalse()")
                        .daemon(false)
                        .build();
                child.start();
                try {
                    child.join();
                } catch (InterruptedException e) {
                    throw new CancellationException("interrupted");
                }
            }, "testDaemonChildFalse()")
            .daemon(true)
            .build();
        test.start();
        assertTrue(test.isDaemon());
        test.join();
        assertFalse(childDaemon.get());
    }
}
