package br.rafael.users.api.exceptions;

public class NotFoundException extends RuntimeException {
    
    public NotFoundException() {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
