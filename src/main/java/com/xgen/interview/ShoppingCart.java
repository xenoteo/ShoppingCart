package com.xgen.interview;

import java.util.*;


/**
 * The implementation of ShoppingCart.
 */
public class ShoppingCart implements IShoppingCart {
    Map<String, Integer> contents = new LinkedHashMap<>();
    Pricer pricer;
    boolean priceFirst = false;

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    public void setPriceFirst(boolean priceFirst) {
        this.priceFirst = priceFirst;
    }

    public void addItem(String itemType, int number) {
        contents.put(itemType, contents.getOrDefault(itemType, 0) + number);
    }

    public void printReceipt() {
        int totalPrice = 0;

        for (String key : contents.keySet()) {
            int price = pricer.getPrice(key) * contents.get(key);
            String priceString = createPriceString(price);

            if (priceFirst) System.out.println(priceString + " - " + key + " - " + contents.get(key));
            else            System.out.println(key + " - " + contents.get(key) + " - " + priceString);

            totalPrice += price;
        }

        String totalPriceString = createPriceString(totalPrice);
        System.out.println("Total: " + totalPriceString);
    }

    private String createPriceString(int price) {
        float priceFloat = (float) price / 100;
        return String.format("€%.2f", priceFloat);
    }

}
