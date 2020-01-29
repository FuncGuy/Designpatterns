/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package observer.solution1;

import java.util.*;
import java.util.concurrent.*;

@SuppressWarnings("deprecation")
public class Trader implements Observer {
    private final StockTicker ticker = new StockTicker();
    private final Set<String> watchlist = new ConcurrentSkipListSet<>();

    public Trader(String... shares) {
        Collections.addAll(watchlist, shares);
    }

    private void registerListeners() {
        ticker.addObserver(this);
    }

    // When the observable notifies us, we first check whether
    // we are watching this particular share.  We then print
    // out the new share price.
    public void update(Observable o, Object arg) {
        SharePrice price = (SharePrice) arg;
        if (watchlist.contains(price.getShare())) {
            System.out.println(price.getShare() + ", " + price.getPrice());
        }
    }

    public static void main(String... args) {
        Trader trader = new Trader("WEZ");
        trader.registerListeners();
        trader.ticker.start();
    }
}
