package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Milk;
import com.estore.api.estoreapi.model.ShoppingCart;

/**
 * Defines the interface for ShoppingCart object persistence
 * 
 * @author SWEN Faculty
 */
public interface ShoppingCartDAO {
    /**
     * Retrieves all {@linkplain Milk milks}
     * 
     * @return An array of {@link Milk milk} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    void addMilk(Milk milk, String userName) throws IOException;

    /**
     * Retrieves all {@linkplain Milk milks}
     * 
     * @return An array of {@link Milk milk} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    void decrementMilk(Milk milk, String userName) throws IOException;
}    