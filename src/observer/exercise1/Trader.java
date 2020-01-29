/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package observer.exercise1;

import java.util.*;
import java.util.concurrent.*;

@SuppressWarnings("deprecation")
public class Trader {
    private final StockTicker ticker = new StockTicker();
    private final Set<String> watchlist = new ConcurrentSkipListSet<>();

    public Trader(String... shares) {
        Collections.addAll(watchlist, shares);
    }

    public void registerListeners() {
        // add ourselves as an observer for the StockTicker
    }

    // When the observable notifies us, we first check whether
    // we are watching this particular share.  We then print
    // out the new share price.

    public static void main(String... args) {
        Trader trader = new Trader("WEZ");
        trader.registerListeners();
        trader.ticker.start();
    }
}
