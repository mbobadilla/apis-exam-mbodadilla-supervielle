package com.supervielle.examen.exception;


import org.apache.commons.lang3.StringUtils;

public class ComunicationException extends TechnicalException implements ISupervielleComunicationException {
    private static final long serialVersionUID = 1L;
    private static final String MSG = "ERROR DE COMUNICATION CON %s";
    private String errorComunicateTo;

    public ComunicationException(String errorComunicateTo, Throwable ex) {
        super(String.format("ERROR DE COMUNICATION CON %s", StringUtils.upperCase(errorComunicateTo)));
        this.errorComunicateTo = errorComunicateTo;
    }

    public String getErrorComunicateTo() {
        return this.errorComunicateTo;
    }
}