package com.xgen.interview;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    @Test
    public void canAddItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);
        sc.addItem("banana", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String result = myOut.toString();

        assertEquals("apple - 1 - €1.00\nbanana - 2 - €4.00\nTotal: €5.00\n", result);
    }

    @Test
    public void supportsPriceFirstFormat() {
        ShoppingCart sc = new ShoppingCart(new Pricer());
        sc.setPriceFirst(true);

        sc.addItem("apple", 1);
        sc.addItem("banana", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String result = myOut.toString();

        assertEquals("€1.00 - apple - 1\n€4.00 - banana - 2\nTotal: €5.00\n", result);
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        assertEquals("crisps - 2 - €0.00\nTotal: €0.00\n", myOut.toString());
    }

}
