package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
public class LoginControllerTest {
    private LoginController loginController;
    private LoginDAO mockLoginDAO;

    @BeforeEach
    public void setupLoginController() {
        mockLoginDAO = mock(LoginDAO.class);
        loginController = new LoginController(mockLoginDAO);
    }

    @Test
    public void testResetPassword() {
        String username = "admin";

        when(mockLoginDAO.resetPassword(username)).thenReturn(true);

        ResponseEntity<String> response = loginController.resetPassword(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testResetPasswordFail() {
        String username = "";

        when(mockLoginDAO.resetPassword(username)).thenReturn(false);

        ResponseEntity<String> response = loginController.resetPassword(username);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
    @Test
    public void testLogin() {
        String username = "admin";
        String password = "admin";

        when(mockLoginDAO.login(username, password)).thenReturn(username);

        ResponseEntity<String> response = loginController.login(new String[]{username, password});

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginFail() {
        String username = "";
        String password = "admin";

        when(mockLoginDAO.login(username, password)).thenReturn(null);

        ResponseEntity<String> response = loginController.login(new String[]{username, password});

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
