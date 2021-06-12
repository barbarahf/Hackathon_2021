package com.example.demo.exceptions;

public class EmailExists extends Exception {
    private static final String DEFAULT_MESSAGE = "The email already exists";

    public EmailExists() {
        super(DEFAULT_MESSAGE);
    }


}