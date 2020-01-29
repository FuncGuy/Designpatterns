/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package observer.solution2;

public class SharePrice {
    private final String share;
    private final double price;

    public SharePrice(String share, double price) {
        this.share = share;
        this.price = price;
    }

    public String getShare() {
        return share;
    }

    public double getPrice() {
        return price;
    }
}
