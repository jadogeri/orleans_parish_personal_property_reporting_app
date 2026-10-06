package com.opao.pp_api.common.exceptions;

public class NonexistentEntityException extends RuntimeException {
    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}
