package com.fola.validator;

import com.fola.model.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ResourceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Resource.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Resource resource = (Resource) object;
    }
}
