package com.metapack.assignments;

/**
 * Custom Exception for handling Integer overflow in {@link AccumulatorImp class}.
 */
public class AccumulatorIntegerOverflowException extends Exception {
    public AccumulatorIntegerOverflowException(String message) {
        super(message);
    }
}
