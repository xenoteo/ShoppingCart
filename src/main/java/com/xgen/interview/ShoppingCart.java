package com.xgen.interview;

import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    HashMap<String, Integer> contents = new HashMap<>();
    Pricer pricer;

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {
        int totalPrice = 0;

        for (String key : contents.keySet()) {
            int price = pricer.getPrice(key) * contents.get(key);
            float priceFloat = (float) price / 100;
            String priceString = String.format("€%.2f", priceFloat);

            System.out.println(key + " - " + contents.get(key) + " - " + priceString);

            totalPrice += price;
        }

        float totalPriceFloat = (float) totalPrice / 100;
        String totalPriceString = String.format("€%.2f", totalPriceFloat);
        System.out.println("Total: " + totalPriceString);
    }
    
}
