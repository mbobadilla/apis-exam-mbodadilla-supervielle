//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hsbc.hbar.mdw.commons.exception;

import brave.Tracer;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.supervielle.examen.exception.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class ExceptionHandlerAdvisor extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvisor.class);
    public static final String ERROR_MSG_GENERIC = "TECHNICAL ERROR. CONTACT TO ADMINISTRATOR";
    public static final String ERROR_MSG_BIND_VALIDATION = "INVALID PARAMETERS";
    public static final String ERROR_FIELD_FORMAT = "%s: %s";
    public static final String ERROR_MSG_ACCESS_DENIED = "ACCESS IS DENIED";
    public static final String ERROR_MSG_COMUNICATION = "COMUNICATION ERROR IN BACKEND %s";
    public static final String ERROR_MSG_TO_LOGGING = "ERROR EXECUTION";
    @Autowired
    private Tracer tracer;

    public ExceptionHandlerAdvisor() {
    }

    private String getTraceId() {
        String result = null;
        if (Objects.isNull(this.tracer)) {
            result = RandomStringUtils.random(16, true, true);
        } else {
            result = "";
        }

        return result;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = null;
        String traceId = null;
        traceId = this.getTraceId();
        customErrorResponse = new CustomErrorResponse(traceId, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase().toUpperCase(), "TECHNICAL ERROR. CONTACT TO ADMINISTRATOR", (List)null);
        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error("ERROR EXECUTION", ex);
        return this.handleExceptionInternal(ex, customErrorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorResponse customErrorResponse = null;
        List<String> errors = null;
        String traceId = null;
        traceId = this.getTraceId();
        errors = (List)ex.getBindingResult().getFieldErrors().stream().map((x) -> {
            return String.format("%s: %s", x.getField(), x.getDefaultMessage());
        }).collect(Collectors.toList());
        customErrorResponse = new CustomErrorResponse(traceId, HttpStatus.BAD_REQUEST.getReasonPhrase().toUpperCase(), "INVALID PARAMETERS", errors);
        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customErrorResponse.setErrors(errors);
        log.error("ERROR EXECUTION", ex);
        return this.handleExceptionInternal(ex, customErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<CustomErrorResponse> handleBusinessException(BusinessException buEx) {
        CustomErrorResponse customErrorResponse = null;
        ResponseEntity<CustomErrorResponse> response = null;
        String traceId = null;
        traceId = this.getTraceId();
        customErrorResponse = new CustomErrorResponse(traceId, buEx.getErrorCode(), buEx.getMessage(), (List)null);
        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.CONFLICT.value());
        log.error("ERROR EXECUTION", buEx);
        response = ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body(customErrorResponse);
        return response;
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundException ex) {
        CustomErrorResponse customErrorResponse = null;
        ResponseEntity<CustomErrorResponse> response = null;
        String traceId = null;
        traceId = this.getTraceId();
        customErrorResponse = new CustomErrorResponse(traceId, HttpStatus.NOT_FOUND.name(), ex.getMessage(), (List)null);
        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        response = ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(customErrorResponse);
        return response;
    }

    @ExceptionHandler({ComunicationException.class})
    public ResponseEntity<CustomErrorResponse> handleComunicationException(ISupervielleComunicationException ex) {
        CustomErrorResponse customErrorResponse = null;
        ResponseEntity<CustomErrorResponse> response = null;
        String traceId = null;
        traceId = this.getTraceId();
        customErrorResponse = new CustomErrorResponse(traceId, HttpStatus.BAD_GATEWAY.name(), String.format("COMUNICATION ERROR IN BACKEND %s", ex.getErrorComunicateTo()), (List)null);
        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.BAD_GATEWAY.value());
        response = ResponseEntity.status(HttpStatus.BAD_GATEWAY).contentType(MediaType.APPLICATION_JSON).body(customErrorResponse);
        log.error("ERROR EXECUTION", ex);
        return response;
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleGenericException(AccessDeniedException ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = null;
        String traceId = null;
        traceId = this.getTraceId();
        customErrorResponse = new CustomErrorResponse(traceId, HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(), "ACCESS IS DENIED", (List)null);
        customErrorResponse.setTimestamp(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        log.error("ERROR EXECUTION", ex);
        return this.handleExceptionInternal(ex, customErrorResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}
