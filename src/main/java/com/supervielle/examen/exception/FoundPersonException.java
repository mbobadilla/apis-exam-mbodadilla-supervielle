package com.supervielle.examen.exception;

import org.springframework.http.HttpStatus;

public class FoundPersonException extends BusinessException{
    public FoundPersonException() {
        super("Person not found", HttpStatus.NOT_FOUND.getReasonPhrase().toUpperCase());
    }


}
