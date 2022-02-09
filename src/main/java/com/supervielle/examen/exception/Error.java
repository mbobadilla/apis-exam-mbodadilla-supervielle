package com.supervielle.examen.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class Error {
    /**
     * Application error code, which is different from HTTP error code.
     */
    private String errorCode;

    /**
     * Short, human-readable summary of the problem.
     */
    private String message;

    /**
     * HTTP status code for this occurrence of the problem, set by the origin server.
     */
    private Integer status;

    /**
     * Url of request that produced the error.
     */
    private String url = "Not available";

    /**
     * Method of request that produced the error.
     */
    private String reqMethod = "Not available";

    /**
     * Timestamp
     */
    private Instant timestamp;
}
