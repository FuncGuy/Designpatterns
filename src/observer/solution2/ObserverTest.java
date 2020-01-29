/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package observer.solution2;

import org.junit.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class ObserverTest {

    @Test
    public void testObserverClassStructure() {
        assertTrue("StockTicker should extend Flow.Publisher", Flow.Publisher.class.isAssignableFrom(StockTicker.class));
        assertTrue("Trader implement Flow.Subscriber", Flow.Subscriber.class.isAssignableFrom(Trader.class));
    }

    private volatile SharePrice wesizwe = null;

    @Test
    public void testStockTicker() throws InterruptedException, IOException {
        StockTicker.main((String[]) null);

        StockTicker ticker = new StockTicker();
        ticker.subscribe(new Flow.Subscriber<>() {
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            public void onNext(SharePrice item) {
                if (item.getShare().equals("WEZ")) {
                    wesizwe = item;
                }
                System.out.printf(Locale.US, "%s: %f%n", item.getShare(), item.getPrice());
            }

            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            public void onComplete() {
                System.out.println("Subscription completed");
            }
        });
        ticker.start();

        for (int i = 0; i < 20 && wesizwe == null; i++) {
            Thread.sleep(100);
        }
        assertNotNull("Share price update was not sent", wesizwe);

        assertEquals("WEZ", wesizwe.getShare());
        assertEquals(9.03, wesizwe.getPrice(), 0.001);

        changeFile(32.33);

        Thread.sleep(1000);

        assertEquals("WEZ", wesizwe.getShare());
        assertEquals(32.33, wesizwe.getPrice(), 0.001);
    }

    private void changeFile(double newSharePrice) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("stocks.csv")) {
            out.println("WEZ," + newSharePrice);
            out.println("DID,12.21");
            out.println("TEL,45.15");
        }
    }
}
