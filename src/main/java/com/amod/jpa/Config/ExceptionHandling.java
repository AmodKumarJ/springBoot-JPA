package com.amod.jpa.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionHandling extends RuntimeException {

    public ExceptionHandling(String message) {
        super(message);
    }
}
