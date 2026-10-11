package com.gift.exception;

/**
 * Виняток, що сигналізує про некоректні фізичні параметри солодощів.
 */
public class InvalidSweetParameterException extends IllegalArgumentException {
    public InvalidSweetParameterException(String message) {
        super(message);
    }
}