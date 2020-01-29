/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package observer.exercise1;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// run this class first to set up stock file
@SuppressWarnings("deprecation")
public class StockTicker extends Observable {
    private final Map<String, Double> shares = new ConcurrentHashMap<>();

    public void start() {
        ScheduledExecutorService timer =
            Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(() -> {
            try {
                readFile();
            } catch (IOException e) {
                System.err.println("Problem reading file: " + e);
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void readFile() throws IOException {
        try (
            BufferedReader in = new BufferedReader(new FileReader("stocks.csv"))
        ) {
            in.lines()
                .map(s -> s.split(","))
                .filter(data -> data.length == 2)
                .map(data -> new SharePrice(data[0], Double.valueOf(data[1])))
                .filter((SharePrice sp) -> !Objects.equals(sp.getPrice(),
                    shares.put(sp.getShare(), sp.getPrice())))
                .forEach((SharePrice sp) -> {
                    // TODO: notify the observers of the new share price
                    // TODO: send a SharePrice object as a parameter
                });
        }
    }


    public static void main(String... args) throws IOException {
        try (
            PrintWriter out = new PrintWriter("stocks.csv")
        ) {
            out.println("WEZ,9.03");
            out.println("DID,12.33");
            out.println("TEL,45.12");
        }
    }
}
