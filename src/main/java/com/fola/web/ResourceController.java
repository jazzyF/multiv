package com.fola.web;

import com.fola.model.Resource;
import com.fola.model.ResourceResponse;
import com.fola.model.ResourceValidationError;
import com.fola.validator.ResourceAttributesConstraints;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
public class ResourceController {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    @PostMapping("/")
    public ResponseEntity<ResourceResponse> index(@Valid @ResourceAttributesConstraints @RequestBody Resource resource) {
        ResourceResponse resourceResponse = new ResourceResponse(34, UUID.randomUUID().toString());
        Set<ConstraintViolation<@Valid Resource>> constraintViolations = VALIDATOR.validate(resource);
        if (constraintViolations.isEmpty()) {
            return new ResponseEntity<>(resourceResponse, HttpStatus.ACCEPTED);
        }
        List<ResourceValidationError> validationErrors = constraintViolations.stream()
                .map(x -> new ResourceValidationError(x.getPropertyPath().toString(), x.getMessage()))
                .collect(Collectors.toList());
        resourceResponse.setCode(-1);
        resourceResponse.getErrors().addAll(validationErrors.stream()
                .map(ResourceValidationError::getErrorMessage)
                .collect(Collectors.toList()));
        resourceResponse.setMessage(null);
        return new ResponseEntity<>(resourceResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResourceResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ResourceResponse resourceResponse = new ResourceResponse();
        resourceResponse.setCode(-1);
        resourceResponse.setMessage("Bad request");
        resourceResponse.getErrors().add("Message not readable");
        try {
            System.out.println(e.getHttpInputMessage().getBody().toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(resourceResponse, HttpStatus.BAD_REQUEST);
    }
}
