package com.supervielle.examen.exception;

import lombok.Data;

@Data
public class ItemNotFoundException extends Throwable {
    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public ItemNotFoundException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public ItemNotFoundException(final String message) {
        super(message);
        this.errMsgKey = ErrorCode.CUSTOMER_NOT_FOUND.getErrMsgKey();
        this.errorCode = ErrorCode.CUSTOMER_NOT_FOUND.getErrCode();
    }
}
