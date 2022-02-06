//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.supervielle.examen.exception;

import org.springframework.core.NestedCheckedException;

public abstract class BusinessException extends NestedCheckedException {
    private static final long serialVersionUID = 1L;
    private String errorCode = null;

    public BusinessException(final String msg, final String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
