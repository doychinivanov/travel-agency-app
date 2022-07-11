package com.travelapp.utils.validations;

import com.travelapp.models.dto.RegisterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordsConstraintValidator implements ConstraintValidator<EqualPasswordsConstraint, Object> {
    @Override
    public void initialize(EqualPasswordsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext context) {
        RegisterDto registerDto = (RegisterDto) candidate;
        return registerDto.getPassword().equals(registerDto.getConfirmPass() );
    }
}
