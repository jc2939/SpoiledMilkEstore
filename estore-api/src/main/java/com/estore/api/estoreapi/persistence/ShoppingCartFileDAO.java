package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Milk;
import com.estore.api.estoreapi.model.ShoppingCart;

/**
 * Implements the functionality for JSON file-based peristance for Milk
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author SWEN Faculty
 */
@Component
public class ShoppingCartFileDAO implements ShoppingCartDAO {
    private static final Logger LOG = Logger.getLogger(ShoppingCartDAO.class.getName());
    Map<String,ShoppingCart> shoppingCart;   // Provides a local cache of the milk objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Milk
                                        // objects and JSON text format written
                                        // to the file
    private String filename;    // Filename to read from and write to


    /**
     * Creates a Milk File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public ShoppingCartFileDAO(@Value("${shoppingcart.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the milks from the file
    }

    /**
     * Generates an array of {@linkplain Milk milks} from the tree map
     * 
     * @return  The array of {@link Milk milks}, may be empty
     */
    private ShoppingCart[] getShoppingCartArray() {
        return getShoppingCartArray(null);
    }

    /**
     * Generates an array of {@linkplain Milk milkArrayList} from the tree map for any
     * {@linkplain Milk milks} that contains the text specified by type
     * <br>
     * If type is null, the array contains all of the {@linkplain Milk milks}
     * in the tree map
     * 
     * @return  The array of {@link Milk milkArray}, may be empty
     */
    private ShoppingCart[] getShoppingCartArray(String type) { // if type == null, no filter
        ArrayList<ShoppingCart> shoppingCartArrayList = new ArrayList<>();

        for (ShoppingCart cart : shoppingCart.values()) {
            if (type == null || cart.getUsername().contains(type)) {
                shoppingCartArrayList.add(cart);
            }
        }

        ShoppingCart[] shoppingCartArray = new ShoppingCart[shoppingCartArrayList.size()];
        shoppingCartArrayList.toArray(shoppingCartArray);
        return shoppingCartArray;
    }

    /**
     * Saves the {@linkplain Milk milks} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Milk milks} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        ShoppingCart[] shoppingCartArray = getShoppingCartArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),shoppingCartArray);
        return true;
    }

    /**
     * Loads {@linkplain Milk milks} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        shoppingCart = new TreeMap<>();

        // Deserializes the JSON objects from the file into an array of milkArray
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        ShoppingCart[] shoppingCartArray = objectMapper.readValue(new File(filename),ShoppingCart[].class);

        // Add each milk to the tree map and keep track of the greatest id
        for (ShoppingCart cart : shoppingCartArray) {
            shoppingCart.put(cart.getUsername(), cart);
        }
        return true;
    }


    /**
     * Generates an array of {@linkplain Milk milks} from the tree map
     */
    public void addMilk(Milk milk, String userName) throws IOException {
        synchronized(shoppingCart) {
            // We create a new milk object because the id field is immutable
            // and we need to assign the next unique id
            shoppingCart.get(userName).incrementItem(milk);
            save(); // may throw an IOException
        }        
    }

    /**
     * Generates an array of {@linkplain Milk milks} from the tree map
     */
    public void decrementMilk(Milk milk, String userName) throws IOException {
        synchronized(shoppingCart) {
            if (shoppingCart.containsKey(userName)){
                shoppingCart.get(userName).decrementItem(milk);
                save(); 
            } 
        }    
    }


}    