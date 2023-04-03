package com.estore.api.estoreapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HashedLogin {
    private static final Logger LOG = Logger.getLogger(Milk.class.getName());

    @JsonProperty("username") private String username;
    @JsonProperty("password-hash") private String passwordHash;

    public HashedLogin(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object != null && object instanceof Login) {
            result = username == ((HashedLogin)object).username && passwordHash == ((HashedLogin)object).passwordHash;
        }
        return result;
    }
}
