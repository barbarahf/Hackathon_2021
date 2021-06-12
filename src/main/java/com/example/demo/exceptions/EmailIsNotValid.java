package com.example.demo.exceptions;


public class EmailIsNotValid extends Exception {

    private static final String DEFAULT_MESSAGE = "The email is not valid";

    public EmailIsNotValid() {
        super(DEFAULT_MESSAGE);
    }

}