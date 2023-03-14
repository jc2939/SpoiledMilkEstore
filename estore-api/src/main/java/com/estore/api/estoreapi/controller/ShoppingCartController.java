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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.MilkDAO;
import com.estore.api.estoreapi.model.Milk;
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
    private MilkDAO milkDao;

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

    @PostMapping("")
    public ResponseEntity<Milk> addMilk(@RequestBody Milk milk) {
        LOG.info("POST /milks " + milk);
        try {
            Milk newMilk = milkDao.createMilk(milk);
            return new ResponseEntity<Milk>(newMilk,HttpStatus.OK);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // /**
    //  * Responds to the GET request for a {@linkplain Milk milk} for the given id
    //  * 
    //  * @param id The id used to locate the {@link Milk milk}
    //  * 
    //  * @return ResponseEntity with {@link Milk milk} object and HTTP status of OK if found<br>
    //  * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
    //  * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
    //  */
    // @GetMapping("/{id}")
    // public ResponseEntity<Milk> getMilk(@PathVariable int id) {
    //     LOG.info("GET /milks/" + id);
    //     try {
    //         Milk milk = milkDao.getMilk(id);
    //         if (milk != null)
    //             return new ResponseEntity<Milk>(milk,HttpStatus.OK);
    //         else
    //             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    //     catch(IOException e) {
    //         LOG.log(Level.SEVERE,e.getLocalizedMessage());
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}    
