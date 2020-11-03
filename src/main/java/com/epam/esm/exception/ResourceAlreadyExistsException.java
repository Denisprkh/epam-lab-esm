package com.epam.esm.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    private int resourceId;

    public ResourceAlreadyExistsException(String message, int resourceId) {
        super(message);
        this.resourceId = resourceId;
    }

    public ResourceAlreadyExistsException() {
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public int getResourceId() {
        return resourceId;
    }
}
