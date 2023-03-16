package com.estore.api.estoreapi.model;
import com.estore.api.estoreapi.model.Milk;
import java.util.logging.Logger;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a Milk entity
 * 
 * @author SWEN Faculty
 * @author Jeremy Smart
 */

public class ShoppingCart {
    private static final Logger LOG = Logger.getLogger(ShoppingCart.class.getName());

    // Package private for tests
    //static final String STRING_FORMAT = "ShoppingCart [username=%s, milksInCart=%s, milksInCartQuantity=%s]";

    @JsonProperty("username") private String username;
    @JsonProperty("milksInCart") private ArrayList<Milk> milksInCart;
    @JsonProperty("milksInCartQuantity") private ArrayList<Integer> milksInCartQuantity;


    /**
     * Create a Shopping Cart with the given id and name
     * @param id The id of the milk
     * @param name The name of the milk
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public ShoppingCart(@JsonProperty("username") String username,  @JsonProperty("milksInCart") ArrayList<Milk> milksInCart,@JsonProperty("milksInCartQuantity") ArrayList<Integer> milksInCartQuantity) {
        this.username = username;
        this.milksInCart = milksInCart;
        this.milksInCartQuantity = milksInCartQuantity;
    }

    /**
     * Sets the type of the milk - necessary for JSON object to Java object deserialization
     * @param type The type of the milk
     */
    public void setMilksInCartQuantity(int index, int amount) {this.milksInCartQuantity.set(index, amount);}
    /**
     * Sets the type of the milk - necessary for JSON object to Java object deserialization
     * @param type The type of the milk
     */
    public void setMilksInCartQuantity(ArrayList<Integer> milksInCartQuantity) {this.milksInCartQuantity = milksInCartQuantity;}

    /**
     * Retrieves the type of the milk
     * @return The type of the milk
     */
    public ArrayList<Integer> getMilksInCartQuantity() {return milksInCartQuantity;}

    /**
     * Retrieves the type of the milk
     * @return The type of the milk
     */
    public Integer getMilksInCartQuantity(int index) {return milksInCartQuantity.get(index);}

    /**
     * Sets the type of the milk - necessary for JSON object to Java object deserialization
     * @param type The type of the milk
     */
    public void setUsername(String username) {this.username = username;}

    /**
     * Retrieves the type of the milk
     * @return The type of the milk
     */
    public String getUsername() {return username;}

    /**
     * Sets the type of the milk - necessary for JSON object to Java object deserialization
     * @param type The type of the milk
     */
    public void setMilksInCart(ArrayList<Milk> milksInCart) {this.milksInCart = milksInCart;}
    /**
     * Sets the type of the milk - necessary for JSON object to Java object deserialization
     * @param type The type of the milk
     */
    public void setMilksInCart(int index, Milk milk) {this.milksInCart.set(index, milk);}

    /**
     * Retrieves the type of the milk
     * @return The type of the milk
     */
    public ArrayList<Milk> getMilksInCart() {return milksInCart;}

    /**
     * Retrieves the type of the milk
     * @return The type of the milk
     */
    public Milk getMilksInCart(int index) {return milksInCart.get(index);}

    public void incrementItem(Milk milk){
        if(milksInCart.contains(milk)){
            milksInCartQuantity.set(milksInCart.indexOf(milk), milksInCartQuantity.get(milksInCart.indexOf(milk)) + 1);
        }else{
            milksInCart.add(milk);
            milksInCartQuantity.add(1);
        }
    }

    public void decrementItem(int id){
        int indexCounter = 0;
        for (Milk milk : milksInCart) {
            if (milk.getId() == id) {
                if(milksInCartQuantity.get(indexCounter) >= 2){
                    milksInCartQuantity.set(indexCounter, milksInCartQuantity.get(indexCounter) - 1);    
                }else{
                    milksInCartQuantity.remove(indexCounter);
                    milksInCart.remove(id);
                }    
            }
            indexCounter++;
        }
    }

    @Override
    public String toString() {
        return  (username + " " + milksInCart.toString() + " " + milksInCartQuantity.toString());
    }
    
}
