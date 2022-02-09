package com.supervielle.examen.exception;

public class BadCredentialsException extends RuntimeException {
    private final String errMsgKey;
    private final String errorCode;

    public BadCredentialsException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public BadCredentialsException(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.BadCredentials.getErrMsgKey();
        this.errorCode = ErrorCode.BadCredentials.getErrCode();
    }
}