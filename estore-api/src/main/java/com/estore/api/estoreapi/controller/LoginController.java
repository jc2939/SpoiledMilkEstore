package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.persistence.LoginDAO;

import java.util.logging.Logger;

@RestController
@RequestMapping("login")
public class LoginController {
    LoginDAO loginDAO;
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    public LoginController(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @PostMapping("")
    public ResponseEntity<String> login(@RequestBody String[] loginData) {
        LOG.info("POST /login");
        String result = loginDAO.login(loginData[0], loginData[1]);
        if (result != null) {
            System.out.println(result);
            return new ResponseEntity<String>(String.format("{\"username\":\"%s\"}",result), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> resetPassword(@PathVariable String username) {
        LOG.info("DELETE /login/"+username);
        if (loginDAO.resetPassword(username)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

