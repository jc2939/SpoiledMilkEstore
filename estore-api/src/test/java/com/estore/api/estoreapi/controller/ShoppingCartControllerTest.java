package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import com.estore.api.estoreapi.model.*;
import com.estore.api.estoreapi.persistence.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Milk Controller class
 * 
 * @author Spoiled Milk Team
 */
@Tag("Controller-tier")
public class ShoppingCartControllerTest {
    private ShoppingCartController shoppingCartController;
    private ShoppingCartDAO mockShoppingCartDAO;

    /**
     * Before each test, create a new MilkController object and inject
     * a mock Milk DAO
     */
    @BeforeEach
    public void setupShoppingCartController() {
        mockShoppingCartDAO = mock(ShoppingCartDAO.class);
        shoppingCartController = new ShoppingCartController(mockShoppingCartDAO);
    }

    @Test
    public void testGetShoppingCart() throws IOException {  // getShoppingCart may throw IOException
        // Setup
        ArrayList<Milk> listOfMilks = new ArrayList<Milk>();
        listOfMilks.add(new Milk(25, "cow", "banana", 2.4, 10, 2.99, "../assets/images/glass-o-milk.jpg"));
        listOfMilks.add(new Milk(26, "goat", "peach", 5.8, 6, 6.24, "../assets/images/glass-o-milk.jpg"));

        ArrayList<Integer> listOfQuantities = new ArrayList<Integer>();
        listOfQuantities.add(1);
        listOfQuantities.add(2);

        ShoppingCart shoppingCart = new ShoppingCart("Testing", listOfMilks, listOfQuantities);
        // When the same id is passed in, our mock Milk DAO will return the Milk object
        when(mockShoppingCartDAO.getShoppingCart(shoppingCart.getUsername())).thenReturn(shoppingCart);

        // Invoke
        ResponseEntity<ShoppingCart> response = shoppingCartController.getShoppingCart(shoppingCart.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(shoppingCart,response.getBody());
    }

    @Test
    public void testGetShoppingCartHandleException() throws Exception { 
        // Setup
        String username = "nonexistant";
        // When getMilk is called on the Mock Milk DAO, throw an IOException
        doThrow(new IOException()).when(mockShoppingCartDAO).getShoppingCart(username);

        // Invoke
        ResponseEntity<ShoppingCart> response = shoppingCartController.getShoppingCart(username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDecrementMilk() throws IOException { // deleteMilk may throw IOException
        // Setup
        String username = "nonexistant";
        int id = 99;
        // When getMilk is called on the Mock Milk DAO, throw an IOException
        when(mockShoppingCartDAO.decrementMilk(id, username)).thenReturn(true);

        // Invoke
        ResponseEntity<ShoppingCart> response = shoppingCartController.decrementMilk(id, username);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }
    
    @Test
    public void testDecrementMilkHandleException() throws IOException { // deleteMilk may throw IOException
        // Setup
        String username = "nonexistant";
        int id = 99;
        // When getMilk is called on the Mock Milk DAO, throw an IOException
        doThrow(new IOException()).when(mockShoppingCartDAO).decrementMilk(id, username);

        // Invoke
        ResponseEntity<ShoppingCart> response = shoppingCartController.decrementMilk(id, username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());

    } 

    @Test
    public void testIncrementMilk() throws IOException { // deleteMilk may throw IOException
        // Setup
        String username = "nonexistant";
        Milk milk = new Milk(26, "goat", "peach", 5.8, 6, 6.24, "../assets/images/glass-o-milk.jpg");
        // When getMilk is called on the Mock Milk DAO, throw an IOException
        when(mockShoppingCartDAO.addMilk(milk, username)).thenReturn(true);

        // Invoke
        ResponseEntity<ShoppingCart> response = shoppingCartController.incrementMilk(milk, username);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }  

    @Test
    public void testIncrementMilkHandleException() throws IOException { // deleteMilk may throw IOException
        // Setup
        String username = "nonexistant";
        Milk milk = new Milk(26, "goat", "peach", 5.8, 6, 6.24, "../assets/images/glass-o-milk.jpg");
        // When getMilk is called on the Mock Milk DAO, throw an IOException
        doThrow(new IOException()).when(mockShoppingCartDAO).addMilk(milk, username);

        // Invoke
        ResponseEntity<ShoppingCart> response = shoppingCartController.incrementMilk(milk, username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    } 
}
