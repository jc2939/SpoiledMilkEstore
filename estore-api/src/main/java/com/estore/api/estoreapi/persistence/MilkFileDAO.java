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

/**
 * Implements the functionality for JSON file-based peristance for Milk
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author SWEN Faculty
 */
@Component
public class MilkFileDAO implements MilkDAO {
    private static final Logger LOG = Logger.getLogger(MilkFileDAO.class.getName());
    Map<Integer,Milk> milks;   // Provides a local cache of the hero objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Milk
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new hero
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Milk File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public MilkFileDAO(@Value("${milk.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the heroes from the file
    }

    /**
     * Generates the next id for a new {@linkplain Milk hero}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Milk heroes} from the tree map
     * 
     * @return  The array of {@link Milk heroes}, may be empty
     */
    private Milk[] getMilkArray() {
        return getMilkArray(null);
    }

    /**
     * Generates an array of {@linkplain Milk heroes} from the tree map for any
     * {@linkplain Milk heroes} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Milk heroes}
     * in the tree map
     * 
     * @return  The array of {@link Milk heroes}, may be empty
     */
    private Milk[] getMilkArray(String containsText) { // if containsText == null, no filter
        ArrayList<Milk> heroArrayList = new ArrayList<>();

        for (Milk hero : milks.values()) {
            if (containsText == null || hero.getType().contains(containsText)) {
                heroArrayList.add(hero);
            }
        }

        Milk[] heroArray = new Milk[heroArrayList.size()];
        heroArrayList.toArray(heroArray);
        return heroArray;
    }

    /**
     * Saves the {@linkplain Milk milks} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Milk milks} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Milk[] milkArray = getMilkArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),milkArray);
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
        milks = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of heroes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Milk[] milkArray = objectMapper.readValue(new File(filename),Milk[].class);

        // Add each milk to the tree map and keep track of the greatest id
        for (Milk milk : milkArray) {
            milks.put(milk.getId(),milk);
            if (milk.getId() > nextId)
                nextId = milk.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Milk[] getMilks() {
        synchronized(milks) {
            return getMilkArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Milk[] findMilk(String containsText) {
        synchronized(milks) {
            return getMilkArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Milk getMilk(int id) {
        synchronized(milks) {
            if (milks.containsKey(id))
                return milks.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Milk createMilk(Milk milk) throws IOException {
        synchronized(this.milks) {
            // We create a new hero object because the id field is immutable
            // and we need to assign the next unique id
            Milk newHero = new Milk(nextId(), milk.getType(), milk.getFlavor(), milk.getVolume());
            this.milks.put(newHero.getId(),newHero);
            save(); // may throw an IOException
            return newHero;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Milk updateMilk(Milk hero) throws IOException {
        synchronized(milks) {
            if (milks.containsKey(hero.getId()) == false)
                return null;  // hero does not exist

            milks.put(hero.getId(),hero);
            save(); // may throw an IOException
            return hero;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteMilk(int id) throws IOException {
        synchronized(milks) {
            if (milks.containsKey(id)) {
                milks.remove(id);
                return save();
            }
            else
                return false;
        }
    }
}
