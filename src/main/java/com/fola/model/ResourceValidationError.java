package com.fola.model;

public class ResourceValidationError {

    private final String field;
    private final String errorMessage;

    public ResourceValidationError(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
