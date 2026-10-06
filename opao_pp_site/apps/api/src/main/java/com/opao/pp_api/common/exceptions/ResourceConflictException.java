package com.opao.pp_api.common.exceptions;

/**
 * @author Joseph Adogeri
 * @since 2026-OCT-05
 * @version 1.0.0
 */

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException(String message) {
        super(message);
    }
}