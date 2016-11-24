package com.github.chabanenk0.Exception;

/**
 * Exception for the wrong path to find files
 */
public class WrongPathException extends Exception {
    public WrongPathException(String message)
    {
        super(message);
    }
}
