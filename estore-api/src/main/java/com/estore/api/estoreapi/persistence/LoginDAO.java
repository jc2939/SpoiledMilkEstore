package com.estore.api.estoreapi.persistence;

public interface LoginDAO {
    String login(String username, String password);
    boolean resetPassword(String username);
}
