package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Milk;

/**
 * Defines the interface for Milk object persistence
 * 
 * @author SWEN Faculty
 */
public interface MilkDAO {
    /**
     * Retrieves all {@linkplain Milk heroes}
     * 
     * @return An array of {@link Milk hero} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Milk[] getMilk() throws IOException;

    /**
     * Finds all {@linkplain Milk heroes} whose name contains the given text
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Milk heroes} whose nemes contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Milk[] findMilk(String containsText) throws IOException;

    /**
     * Retrieves a {@linkplain Milk hero} with the given id
     * 
     * @param id The id of the {@link Milk hero} to get
     * 
     * @return a {@link Milk hero} object with the matching id
     * <br>
     * null if no {@link Milk hero} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Milk getMilk(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain Milk hero}
     * 
     * @param hero {@linkplain Milk hero} object to be created and saved
     * <br>
     * The id of the hero object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Milk hero} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Milk createMilk(Milk hero) throws IOException;

    /**
     * Updates and saves a {@linkplain Milk hero}
     * 
     * @param {@link Milk hero} object to be updated and saved
     * 
     * @return updated {@link Milk hero} if successful, null if
     * {@link Milk hero} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Milk updateMilk(Milk hero) throws IOException;

    /**
     * Deletes a {@linkplain Milk hero} with the given id
     * 
     * @param id The id of the {@link Milk hero}
     * 
     * @return true if the {@link Milk hero} was deleted
     * <br>
     * false if hero with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteMilk(int id) throws IOException;
}
