package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.Login;
import com.estore.api.estoreapi.model.Milk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("Persistence-tier")
public class LoginFileDAOTest {
    LoginFileDAO loginFileDAO;
    String[][] testLogins;
    ObjectMapper mockObjectMapper;

    /**
     * Create and inject a Mock Object Mapper to isolate the tests
     * from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupMilkFileDAO() throws IOException
    {
        mockObjectMapper = mock(ObjectMapper.class);
        testLogins = new String[][]{{"admin","92668751"}};

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper.readValue(new File("random.txt"), String[][].class)).thenReturn(testLogins);
        loginFileDAO = new LoginFileDAO("random.txt", mockObjectMapper);
    }

    @Test
    public void testLogin() throws IOException {
        String username = "admin";
        String password = "admin";

        String result = loginFileDAO.login(new Login(username, password));
        assertEquals(result, "admin");
    }
    @Test
    public void testLoginWrongPassword() throws IOException {
        String username = "admin";
        String password = "admin1";

        String result = loginFileDAO.login(new Login(username, password));
        assertEquals(result, null);
    }
    @Test
    public void testLoginNewAccount() throws IOException {
        String username = "admin1";
        String password = "admin";

        String result = loginFileDAO.login(new Login(username, password));
        assertEquals(result, "admin1");
    }
}
