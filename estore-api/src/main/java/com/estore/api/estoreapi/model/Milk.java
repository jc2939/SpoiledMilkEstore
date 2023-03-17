package com.estore.api.estoreapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a Milk entity
 * 
 * @author SWEN Faculty
 * @author Jeremy Smart
 */
public class Milk {
    private static final Logger LOG = Logger.getLogger(Milk.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Milk [id=%d, name=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("type") private String type;
    @JsonProperty("flavor") private String flavor;
    @JsonProperty("volume") private double volume;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("price") private double price;
    @JsonProperty("imageUrl") String imageUrl;


    /**
     * Create a milk with the given id and name
     * @param id The id of the milk
     * @param name The name of the milk
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Milk(@JsonProperty("id") int id, @JsonProperty("type") String type, @JsonProperty("flavor") String flavor, @JsonProperty("volume") double volume, @JsonProperty("quantity") int quantity, @JsonProperty("price") double price, @JsonProperty("imageUrl") String imageUrl) {
        this.id = id;
        this.type = type;
        this.flavor = flavor;
        this.volume = volume;
        this.quantity = quantity;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    /**
     * Retrieves the id of the milk
     * @return The id of the milk
     */
    public int getId() {return id;}

    /**
     * Sets the type of the milk - necessary for JSON object to Java object deserialization
     * @param type The type of the milk
     */
    public void setType(String type) {this.type = type;}

    /**
     * Retrieves the type of the milk
     * @return The type of the milk
     */
    public String getType() {return type;}

    /**
     * Sets the flavor of the milk - necessary for JSON object to Java object deserialization
     * @param name The flavor of the milk
     */
    public void setFlavor(String flavor) {this.flavor = flavor;}

    /**
     * Retrieves the flavor of the milk
     * @return The flavor of the milk
     */
    public String getFlavor() {return flavor;}

    /**
     * Sets the volume of the milk - necessary for JSON object to Java object deserialization
     * @param name The volume of the milk
     */
    public void setVolume(double volume) {this.volume = volume;}

    /**
     * Retrieves the volume of the milk
     * @return The volume of the milk
     */
    public double getVolume() {return volume;}

    /**
     * Sets the quantity of the milk - necessary for JSON object to Java object deserialization
     * @param name The quantity of the milk
     */
    public void setQuantity(int quantity) {this.quantity = quantity;}

    /**
     * Retrieves the quantity of the milk
     * @return The quantity of the milk
     */
    public int getQuantity() {return quantity;}

    /**
     * Sets the price of the milk - necessary for JSON object to Java object deserialization
     * @param name The price of the milk
     */
    public void setPrice(double price) {this.price = price;}

    /**
     * Retrieves the price of the milk
     * @return The price of the milk
     */
    public double getPrice() {return price;}

    /**
     * Sets the image url of the milk - necessary for JSON object to Java object deserialization
     * @param imageUrl The image url of the milk
     */
    public void setURL(String imageUrl) {this.imageUrl = imageUrl;}

    /**
     * Retrieves the image url of the milk
     * @return The image url of the milk
     */
    public String getImageUrl() {return imageUrl;}


    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Milk)
        {
            sameSame = this.id == ((Milk) object).id;
        }

        return sameSame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,type);
    }
}