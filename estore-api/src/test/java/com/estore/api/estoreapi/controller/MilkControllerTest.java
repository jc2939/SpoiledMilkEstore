package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

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
public class MilkControllerTest {
    private MilkController milkController;
    private MilkDAO mockMilkDAO;

    /**
     * Before each test, create a new MilkController object and inject
     * a mock Milk DAO
     */
    @BeforeEach
    public void setupMilkController() {
        mockMilkDAO = mock(MilkDAO.class);
        milkController = new MilkController(mockMilkDAO);
    }

    @Test
    public void testGetMilk() throws IOException {  // getMilk may throw IOException
        // Setup
        Milk milk = new Milk(1,"Oat", "Dragonfruit", 35.0, 50, 99.99);
        // When the same id is passed in, our mock Milk DAO will return the Milk object
        when(mockMilkDAO.getMilk(milk.getId())).thenReturn(milk);

        // Invoke
        ResponseEntity<Milk> response = milkController.getMilk(milk.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(milk,response.getBody());
    }

    @Test
    public void testGetMilkNotFound() throws Exception { // createMilk may throw IOException
        // Setup
        int milkID = 99;
        // When the same id is passed in, our mock Milk DAO will return null, simulating
        // no milk found
        when(mockMilkDAO.getMilk(milkID)).thenReturn(null);

        // Invoke
        ResponseEntity<Milk> response = milkController.getMilk(milkID);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetMilkHandleException() throws Exception { // createMilk may throw IOException
        // Setup
        int milkID = 99;
        // When getMilk is called on the Mock Milk DAO, throw an IOException
        doThrow(new IOException()).when(mockMilkDAO).getMilk(milkID);

        // Invoke
        ResponseEntity<Milk> response = milkController.getMilk(milkID);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteMilk() throws IOException { // deleteMilk may throw IOException
        // Setup
        int milkID = 99;
        // when deleteMilk is called return true, simulating successful deletion
        when(mockMilkDAO.deleteMilk(milkID)).thenReturn(true);

        // Invoke
        ResponseEntity<Milk> response = milkController.deleteMilk(milkID);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteMilkNotFound() throws IOException { // deleteMilk may throw IOException
        // Setup
        int milkID = 99;
        // when deleteMilk is called return false, simulating failed deletion
        when(mockMilkDAO.deleteMilk(milkID)).thenReturn(false);

        // Invoke
        ResponseEntity<Milk> response = milkController.deleteMilk(milkID);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteMilkHandleException() throws IOException { // deleteMilk may throw IOException
        // Setup
        int milkID = 99;
        // When deleteMilk is called on the Mock Milk DAO, throw an IOException
        doThrow(new IOException()).when(mockMilkDAO).deleteMilk(milkID);

        // Invoke
        ResponseEntity<Milk> response = milkController.deleteMilk(milkID);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
}
