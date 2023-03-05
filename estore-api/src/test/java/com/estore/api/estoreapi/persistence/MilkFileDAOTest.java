package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.Milk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("Persistence-tier")
public class MilkFileDAOTest {
    MilkFileDAO milkFileDAO;
    Milk[] testMilks;
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
        testMilks = new Milk[4];
        testMilks[0] = new Milk(10, "cow", "banana", 2.5, 8, 2.95);
        testMilks[1] = new Milk(11, "oat", "chocolate", 4.2, 5, 4.50);
        testMilks[2] = new Milk(12, "goat", "strawberry", 3.9, 11, 4.23);
        testMilks[3] = new Milk(13, "almond", "pumpkin", 5.7, 14, 6.03);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper.readValue(new File("random.txt"), Milk[].class))
            .thenReturn(testMilks);
        milkFileDAO = new MilkFileDAO("random.txt", mockObjectMapper);
    }

    @Test
    public void testGetMilks()
    {
        Milk[] milks = milkFileDAO.getMilks();
        // Analyze
        assertEquals(milks.length, testMilks.length);
        for (int i = 0; i < testMilks.length; i++)
        {
            assertEquals(milks[i], testMilks[i]);
        }
    }

    @Test
    public void testSearchMilks()
    {
        Milk[] milks = milkFileDAO.searchMilks("oat");
        // Analyze
        assertEquals(milks.length, 2);
        assertEquals(milks[0], testMilks[1]);
        assertEquals(milks[1], testMilks[2]);
    }

    @Test
    public void testGetMilk() {
        // Invoke
        Milk milk = milkFileDAO.getMilk(10);

        // Analyze
        assertEquals(milk,testMilks[0]);
    }

    @Test
    public void testDeleteMilk() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> milkFileDAO.deleteMilk(10),
                            "Unexpected exception thrown");

        // Analyze
        assertEquals(result, true);
        // We check the internal tree map size against the length
        // of the test milks array - 1 (because of the delete)
        // Because milks attribute of MilkFileDAO is package private
        // we can access it directly
        assertEquals(milkFileDAO.milks.size(),testMilks.length-1);
    }

}
