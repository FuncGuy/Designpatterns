/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package observer.solution2;

import java.util.*;
import java.util.concurrent.*;

public class Trader implements Flow.Subscriber<SharePrice> {
    private final StockTicker ticker = new StockTicker();
    private final Set<String> watchlist = new ConcurrentSkipListSet<>();

    public Trader(String... shares) {
        Collections.addAll(watchlist, shares);
    }

    public static void main(String... args) {
        Trader trader = new Trader("WEZ");
        trader.ticker.subscribe(trader);
        trader.ticker.start();
    }

    public void onSubscribe(Flow.Subscription subscription) {
    }

    public void onNext(SharePrice item) {
        if (watchlist.contains(item.getShare())) {
            System.out.println(item.getShare() + ", " + item.getPrice());
        }
    }

    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    public void onComplete() {

    }
}
