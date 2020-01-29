/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package composite.exercise1;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class CompositeTest {
    @Test
    public void testComplicatedTree() {
        Contact a = new Person("a@a.ws");
        Contact b = new Person("b@a.ws");
        Contact c = new Person("c@a.ws");
        Contact d = new Person("d@a.ws");
        Contact e = new Person("e@a.ws");
        Contact f = new Person("f@a.ws");
        Contact dl1 = new DistributionList();
        Contact dl2 = new DistributionList();
        Contact dl3 = new DistributionList();
        dl1.add(dl2);
        dl3.add(a);
        dl3.add(b);
        dl3.add(c);
        dl3.add(d);
        dl1.add(dl3);
        dl1.add(e);
        dl1.add(f);
        ContactIterator it;
        it = new ContactIterator(a);
        assertSame(a, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(b);
        assertSame(b, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(c);
        assertSame(c, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(d);
        assertSame(d, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(e);
        assertSame(e, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(f);
        assertSame(f, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(dl1);
        assertSame(a, it.next());
        assertSame(b, it.next());
        assertSame(c, it.next());
        assertSame(d, it.next());
        assertSame(e, it.next());
        assertSame(f, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(dl2);
        assertTrue(!it.hasNext());
        it = new ContactIterator(dl3);
        assertSame(a, it.next());
        assertSame(b, it.next());
        assertSame(c, it.next());
        assertSame(d, it.next());
        assertFalse(it.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void testRootLeafRemove() {
        ContactIterator it = new ContactIterator(new Person("a@a.ws"));
        it.remove();
    }

    @Test
    public void testRemove() {
        Contact a = new Person("a@a.ws");
        Contact b = new Person("b@a.ws");
        Contact c = new Person("c@a.ws");
        Contact d = new Person("d@a.ws");
        Contact e = new Person("e@a.ws");
        Contact f = new Person("f@a.ws");
        Contact dl1 = new DistributionList();
        Contact dl2 = new DistributionList();
        Contact dl3 = new DistributionList();
        dl1.add(dl2);
        dl3.add(a);
        dl3.add(b);
        dl3.add(c);
        dl3.add(d);
        dl1.add(dl3);
        dl1.add(e);
        dl1.add(f);
        ContactIterator it;

        System.out.println("Removing c@c.ws");
        it = new ContactIterator(dl1);
        assertSame(a, it.next());
        assertSame(b, it.next());
        assertSame(c, it.next());
        it.remove();
        assertSame(d, it.next());
        assertSame(e, it.next());
        assertSame(f, it.next());
        assertFalse(it.hasNext());

        System.out.println("Removing f@f.ws");
        it = new ContactIterator(dl1);
        assertSame(a, it.next());
        assertSame(b, it.next());
        assertSame("c@c.ws was not removed from the composite tree", d, it.next());
        assertSame(e, it.next());
        assertSame(f, it.next());
        it.remove();
        assertFalse(it.hasNext());

        System.out.println("Removing a@a.ws");
        it = new ContactIterator(dl1);
        assertSame(a, it.next());
        it.remove();
        assertSame(b, it.next());
        assertSame(d, it.next());
        assertSame(e, it.next());
        assertFalse("f@f.ws was not removed from the composite tree", it.hasNext());

        System.out.println("Removing d@d.ws");
        it = new ContactIterator(dl1);
        assertSame("a@a.ws was not removed from the composite tree", b, it.next());
        assertSame(d, it.next());
        it.remove();
        assertSame(e, it.next());
        assertFalse(it.hasNext());

        System.out.println("Removing e@e.ws");
        it = new ContactIterator(dl1);
        assertSame(b, it.next());
        assertSame("d@d.ws was not removed from the composite tree", e, it.next());
        it.remove();
        assertFalse(it.hasNext());

        System.out.println("Removing b@b.ws");
        it = new ContactIterator(dl1);
        assertSame(b, it.next());
        it.remove();
        assertFalse("e@e.ws was not removed from the composite tree", it.hasNext());

        System.out.println("Empty ContactIterator");
        it = new ContactIterator(dl1);
        assertFalse("b@b.ws was not removed from the composite tree", it.hasNext());
    }


    @Test
    public void testContactHasDefaultsForLeaf() {
        Contact contact = new Contact() {
            public void sendMail(String msg) {
                // do nothing
            }
        };
        assertTrue("Usually we make the Component have the defaults for all leaves", contact.isLeaf());
        assertTrue("Usually we make the Component have the defaults for all leaves", contact.children() instanceof NullIterator);
    }

    @Test
    public void testNullIteratorUsed() {
        Contact contact = new Contact() {
            public void sendMail(String msg) {
                // do nothing
            }
        };
        Iterator<Contact> it1 = contact.children();
        Iterator<Contact> it2 = new Person("bla@aol.com").children();
        assertSame("Leaf iterators should give back the same shared instance", it1, it2);
    }

    @Test
    public void testDistributionList() {
        DistributionList list = new DistributionList();
        assertFalse("Even an empty DistributionList is a non-leaf", list.isLeaf());
        assertFalse("DistributionList will never return a NullIterator", list.children() instanceof NullIterator);
    }
}
