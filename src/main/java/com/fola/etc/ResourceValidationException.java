package com.fola.etc;

import com.fola.model.ResourceValidationError;

import java.util.Collections;
import java.util.List;

public class ResourceValidationException extends RuntimeException {

    private List<ResourceValidationError> validationErrors;

    public ResourceValidationException(List<ResourceValidationError> validationErrors) {
        super("Invalid Resource Payload");
        this.validationErrors = Collections.unmodifiableList(validationErrors);
    }

    public List<ResourceValidationError> getValidationErrors() {
        return validationErrors;
    }
}
