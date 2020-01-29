/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package observer.solution1;

import org.junit.*;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

@SuppressWarnings("deprecation")
//DON'T CHANGE
public class ObserverTest {
    @Test
    public void testObserverClassStructure() {
        assertTrue("StockTicker should extends Observable", Observable.class.isAssignableFrom(StockTicker.class));
        assertTrue("Trader implement Observer", Observer.class.isAssignableFrom(Trader.class));
    }

    private volatile SharePrice wesizwe = null;

    @Test
    public void testStockTicker() throws InterruptedException, IOException {
        StockTicker.main((String[]) null);

        StockTicker ticker = new StockTicker();
        Observable observable = Observable.class.cast(ticker);
        observable.addObserver((o, arg) -> {
            SharePrice share = (SharePrice) arg;
            if (share.getShare().equals("WEZ")) {
                wesizwe = share;
            }
            System.out.printf(Locale.US, "%s: %f%n", share.getShare(), share.getPrice());
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
