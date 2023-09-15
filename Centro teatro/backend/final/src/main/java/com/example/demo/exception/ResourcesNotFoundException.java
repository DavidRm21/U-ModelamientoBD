package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourcesNotFoundException  extends RuntimeException{

    private static final long serianVersionUID = 1L;

    public ResourcesNotFoundException(String message) {
        super(message);
    }
}
