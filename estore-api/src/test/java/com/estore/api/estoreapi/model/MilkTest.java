package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class MilkTest {
    @Test
    public void testMilk()
    {
        // Setup
        String expectedType = "cow";
        String expectedFlavor = "banana";
        double expectedVolume = 4.7;
        int expectedQuantity = 6;
        double expectedPrice = 4.85;
        String expectedImageUrl = "../assets/images/glass-o-milk.jpg";
        // Invoke
        Milk milk = new Milk(10, expectedType, expectedFlavor, expectedVolume, expectedQuantity, expectedPrice, expectedImageUrl);
        // Analyze
        assertEquals(expectedType, milk.getType());
        assertEquals(expectedFlavor, milk.getFlavor());
        assertEquals(expectedVolume, milk.getVolume());
        assertEquals(expectedQuantity, milk.getQuantity());
        assertEquals(expectedPrice, milk.getPrice());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String type = "goat";
        String flavor = "vanilla";
        double volume = 4.5;
        int quantity = 7;
        double price = 4.99;
        String imageUrl = "../assets/images/glass-o-milk.jpg";
        String expected_string = String.format(Milk.STRING_FORMAT, id, type, flavor, volume, quantity, price, imageUrl);
        Milk milk = new Milk(id, type, flavor, volume, quantity, price, imageUrl);

        // Invoke
        String actual_string = milk.toString();

        // Analyze
        assertEquals(expected_string, actual_string);
    }
    
}
