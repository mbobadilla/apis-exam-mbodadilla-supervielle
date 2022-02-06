package com.supervielle.examen.exception;

import org.springframework.core.NestedRuntimeException;

public abstract class TechnicalException extends NestedRuntimeException {
    private static final long serialVersionUID = 1L;
    private String orignalMessage;

    public TechnicalException(String msg) {
        super(msg);
        this.orignalMessage = msg;
    }
}