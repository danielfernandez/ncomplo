package org.eleventhlabs.ncomplo.exceptions;

public class InternalErrorException extends RuntimeException {

    private static final long serialVersionUID = -3415582816731719368L;

    public InternalErrorException() {
        super();
    }

    public InternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(Throwable cause) {
        super(cause);
    }
    
}
