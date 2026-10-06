package com.opao.pp_api.common.exceptions;

/**
 * @author Joseph Adogeri
 * @since 2026-OCT-05
 * @version 1.0.0
 */

public class AccountLockedException extends RuntimeException {
    public AccountLockedException(String message) {
        super(message);
    }
}