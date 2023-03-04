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
        // Invoke
        Milk milk = new Milk(10, expectedType, expectedFlavor, expectedVolume, expectedQuantity, expectedPrice);
        // Analyze
        assertEquals(expectedType, milk.getType());
        assertEquals(expectedFlavor, milk.getFlavor());
        assertEquals(expectedVolume, milk.getVolume());
        assertEquals(expectedQuantity, milk.getQuantity());
        assertEquals(expectedPrice, milk.getPrice());
    }
    
}
