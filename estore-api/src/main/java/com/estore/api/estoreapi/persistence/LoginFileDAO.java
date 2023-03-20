package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoginFileDAO implements LoginDAO {
    Map<String,String> logins;  // Provides a local cache of the login pairs
                                // so that we don't need to read from the file
                                // each 
    private ObjectMapper objectMapper;  // Provides conversion between Milk
                                        // objects and JSON text format written
                                        // to the file
    private String filename; // Filename for logins

    public LoginFileDAO(@Value("${logins.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.objectMapper = objectMapper;
        this.filename = filename;
        load();
    }

    private boolean load() throws IOException {
        logins = new TreeMap<>();

        String[][] loginArray = objectMapper.readValue(new File(filename), String[][].class);
        for (String[] login : loginArray) {
            logins.put(login[0], login[1]);
        }

        return true;
    }

    @Override
    public String login(String username, String password) {
        System.out.println(logins);
        if (username == null || password == null) {
            return null;
        }
        if (!logins.containsKey(username)) {
            logins.put(username, String.valueOf(password.hashCode()));
            return username;
        }
        if (logins.get(username).equals(String.valueOf(password.hashCode()))) {
            return username;
        }
        return null;
    }

    @Override
    public boolean resetPassword(String username) {
        if (username == null) {
            return false;
        }
        logins.remove(username);
        return true;
    }
}
