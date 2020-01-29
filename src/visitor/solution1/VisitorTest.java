/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package visitor.solution1;

import org.junit.*;

import java.lang.reflect.*;
import java.util.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class VisitorTest {
    @Test
    public void testCountingVisitor() throws Throwable {
        Contact a = new Person("a@a.ws");
        Contact b = new Person("b@a.ws");
        Contact c = new Person("c@a.ws");
        Contact d = new Person("d@a.ws");
        Contact e = new Person("e@a.ws");
        Contact f = new Person("f@a.ws");
        Contact root = new DistributionList();
        Contact dl1 = new DistributionList();
        Contact dl2 = new DistributionList();
        root.add(dl1);
        dl2.add(a);
        dl2.add(b);
        dl2.add(c);
        dl2.add(d);
        root.add(dl2);
        root.add(e);
        root.add(f);

        CountingVisitor cv = new CountingVisitor();

        accept(root, cv);

        assertEquals(6, cv.getNumberOfLeaves());
        assertEquals(3, cv.getNumberOfComposites());
        assertEquals(2.67, cv.getAverageNumberOfChildrenPerComposite(), 0.01);
        assertEquals(3.56, cv.getVarianceNumberOfChildrenPerComposite(), 0.01);
    }

    private void accept(Contact root, Object visitor) throws Throwable {
        Method accept = findAcceptMethod();
        try {
            accept.invoke(root, visitor);
        } catch (InvocationTargetException e1) {
            throw e1.getCause();
        }
    }


    @Test
    public void testVisitorIsInterface() {
        Class<?> visitorIntf = findVisitorInterface();
        assertTrue("Visitor needs to be an interface", visitorIntf.isInterface());
    }

    private Class<?> findVisitorInterface() {
        Collection<Class<?>> interfaces = new HashSet<>();
        Collections.addAll(interfaces, CountingVisitor.class.getInterfaces());
        Collections.addAll(interfaces, EmailGatheringVisitor.class.getInterfaces());
        interfaces.remove(Iterable.class);
        assertEquals("Both CountingVisitor and EmailGatheringVisitor should implement the same Visitor interface",
            1, interfaces.size());
        return interfaces.iterator().next();
    }

    @Test
    public void testThatDistributionListForwardsVisitorToLeaves() throws Throwable {
        Contact a = new Person("a@a.ws");
        Contact b = new Person("b@a.ws");
        Contact c = new Person("c@a.ws");
        Contact d = new Person("d@a.ws");
        Contact e = new Person("e@a.ws");
        Contact f = new Person("f@a.ws");
        Contact root = new DistributionList();
        Contact dl1 = new DistributionList();
        Contact dl2 = new DistributionList();
        root.add(dl1);
        dl2.add(a);
        dl2.add(b);
        dl2.add(c);
        dl2.add(d);
        root.add(dl2);
        root.add(e);
        root.add(f);

        final List<Person> visitedPersons = new ArrayList<>();
        final Class<?> visitorIntf = findVisitorInterface();
        Object myVisitor = Proxy.newProxyInstance(
            visitorIntf.getClassLoader(),
            new Class<?>[]{visitorIntf},
            (proxy, m, args) -> {
                if (m.getParameterTypes()[0] == Person.class) {
                    visitedPersons.add((Person) args[0]);
                }
                return null;
            }
        );

        accept(root, myVisitor);

        assertEquals(6, visitedPersons.size());
    }

    private Method findAcceptMethod() {
        for (Method method : Contact.class.getDeclaredMethods()) {
            if (method.getName().equals("accept")) {
                return method;
            }
        }
        fail("Contact needs an accept() method that takes a visitor as a parameter");
        return null; // will never be called - we fail before
    }

    @Test
    public void testEmailGatheringVisitor() throws Throwable {
        Contact root = new DistributionList();
        Contact dl1 = new DistributionList();
        Contact dl2 = new DistributionList();
        root.add(dl1);
        dl2.add(new Person("b@a.ws"));
        dl2.add(new Person("a@a.ws"));
        dl2.add(new Person("d@a.ws"));
        dl2.add(new Person("c@a.ws"));
        dl1.add(new Person("d@a.ws"));
        root.add(new Person("e@a.ws"));
        root.add(dl2);
        root.add(new Person("f@a.ws"));

        EmailGatheringVisitor cv = new EmailGatheringVisitor();

        accept(root, cv);

        Iterator<String> it = cv.iterator();
        assertEquals("a@a.ws", it.next());
        assertEquals("b@a.ws", it.next());
        assertEquals("c@a.ws", it.next());
        assertEquals("d@a.ws", it.next());
        assertEquals("e@a.ws", it.next());
        assertEquals("f@a.ws", it.next());
    }
}
