package com.supervielle.examen.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException {
    private static final long serialVersionUID = 1L;
    public static final String MSG = "Element not found";

    public NotFoundException() {
        super("Element not found", HttpStatus.NOT_FOUND.getReasonPhrase().toUpperCase());
    }
}
