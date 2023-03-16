package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.MilkDAO;
import com.estore.api.estoreapi.model.Milk;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.persistence.ShoppingCartDAO;

/**
 * Handles the REST API requests for the Milk resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author SWEN Faculty
 */

@RestController
@RequestMapping("cart")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCartDAO shoppingCartDAO;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param milkDao The {@link MilkDAO Milk Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public ShoppingCartController(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    /**
     * Creates a {@linkplain Milk milk} with the provided milk object
     * 
     * @param milk - The {@link Milk milk} to create
     * 
     * @return ResponseEntity with created {@link Milk milk} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Milk milk} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<ShoppingCart> incrementMilk(@RequestBody Milk milk, String userName) {
        LOG.info("Post /milks " + userName);
        try {
            shoppingCartDAO.addMilk(milk, userName);
            return new ResponseEntity<ShoppingCart>(HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a {@linkplain Milk milk} with the given id
     * 
     * @param id The id of the {@link Milk milk} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("")
    public ResponseEntity<ShoppingCart> decrementMilk(@PathVariable Milk milk, String userName) {
        LOG.info("DELETE /milks/" + userName);
        try {
            boolean deleted = shoppingCartDAO.decrementMilk(milk, userName);
            if (deleted)
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Responds to the GET request for all {@linkplain Milk listOfMilks}
     * 
     * @return ResponseEntity with array of {@link Milk listOfMilks} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<ShoppingCart[]> getShoppingCarts() {
        LOG.info("GET /carts");
        
        try {
            ShoppingCart listOfCarts[] = shoppingCartDAO.getShoppingCarts();
            System.out.println(listOfCarts[0].toString());
            System.out.println(listOfCarts[1].toString());
            return new ResponseEntity<ShoppingCart[]>(listOfCarts, HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Milk listOfMilks}
     * 
     * @return ResponseEntity with array of {@link Milk listOfMilks} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{name}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable String name) {
        LOG.info("GET /carts");
        System.out.println(name);
        try {
            ShoppingCart cart = shoppingCartDAO.getShoppingCart(name);
            System.out.println(cart.toString());
            return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}    
