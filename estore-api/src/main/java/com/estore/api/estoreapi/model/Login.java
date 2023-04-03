package com.estore.api.estoreapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {
    private static final Logger LOG = Logger.getLogger(Milk.class.getName());

    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object != null && object instanceof Login) {
            result = username == ((Login)object).username && password == ((Login)object).password;
        }
        return result;
    }
}
