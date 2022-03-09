package io.github.ivanbabura.shorturlgenerator.exceptions;

public class IncorrectUrlException extends RuntimeException {
    public IncorrectUrlException(String message) {
        super(message);
    }
}