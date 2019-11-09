package com.fola.validator;

import com.fola.model.Resource;

import javax.validation.*;
import java.util.Set;

public class ResourceConstraintValidator implements ConstraintValidator<ResourceAttributesConstraints, Resource> {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    @Override
    public boolean isValid(Resource resource, ConstraintValidatorContext context) {
        Set<ConstraintViolation<Resource>> constraintViolations = VALIDATOR.validate(resource);
        /*
        if (constraintViolations.isEmpty()) {
            return true;
        }
        List<ResourceValidationError> validationErrors = constraintViolations.stream()
                .map(x -> new ResourceValidationError(x.getPropertyPath().toString(), x.getMessage()))
                .collect(Collectors.toList());
        throw new ResourceValidationException(Collections.unmodifiableList(validationErrors));*/
        return constraintViolations.isEmpty();
    }
}
