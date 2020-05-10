package com.example.webproject;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomValidationController implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Patient.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Patient patient = (Patient) target;
        if (patient.getFirstName().equalsIgnoreCase("RTE")) {
            errors.rejectValue("firstName", null, "The patient name cannot be RTE");
        }
    }
}
