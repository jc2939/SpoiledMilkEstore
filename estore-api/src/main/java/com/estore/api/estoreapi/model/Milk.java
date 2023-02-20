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
    public Milk(@JsonProperty("id") int id, @JsonProperty("type") String type, @JsonProperty("flavor") String flavor, @JsonProperty("volume") double volume) {
        this.id = id;
        this.type = type;
        this.flavor = flavor;
        this.volume = volume;
    }

    /**
     * Retrieves the id of the milk
     * @return The id of the milk
     */
    public int getId() {return id;}

    /**
     * Sets the name of the milk - necessary for JSON object to Java object deserialization
     * @param type The type of the milk
     */
    public void setType(String type) {this.type = type;}

    /**
     * Retrieves the name of the milk
     * @return The name of the milk
     */
    public String getType() {return type;}

    /**
     * Sets the name of the milk - necessary for JSON object to Java object deserialization
     * @param name The name of the milk
     */
    public void setFlavor(String flavor) {this.flavor = flavor;}

    /**
     * Retrieves the name of the milk
     * @return The name of the milk
     */
    public String getFlavor() {return flavor;}

    /**
     * Sets the name of the milk - necessary for JSON object to Java object deserialization
     * @param name The name of the milk
     */
    public void setVolume(double volume) {this.volume = volume;}

    /**
     * Retrieves the name of the milk
     * @return The name of the milk
     */
    public double getVolume() {return volume;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,type);
    }
}