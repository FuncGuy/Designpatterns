/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package proxy.exercise2;

import java.lang.reflect.*;
import java.util.function.*;

//DON'T CHANGE
public class Proxies {
    public static <T> T virtual(ClassLoader loader, Class<T> subject, Supplier<? extends T> supplier) {
        return subject.cast(Proxy.newProxyInstance(
            loader, new Class<?>[]{subject}, new VirtualProxy<T>(supplier)));
    }

    public static <T> T virtual(Class<T> subject, Supplier<? extends T> supplier) {
        return virtual(Thread.currentThread().getContextClassLoader(),
            subject, supplier);
    }

    private static class VirtualProxy<T> implements InvocationHandler {
        private final Supplier<? extends T> supplier;
        private T realSubject;

        public VirtualProxy(Supplier<? extends T> supplier) {
            this.supplier = supplier;
        }

        private T realSubject() {
            synchronized (this) {
                if (realSubject == null)
                    realSubject = supplier.get();
            }
            return realSubject;
        }

        // called whenever any method is called on interface
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(realSubject(), args);
        }
    }
}
