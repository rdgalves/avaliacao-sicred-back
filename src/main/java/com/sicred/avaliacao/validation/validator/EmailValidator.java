package com.sicred.avaliacao.validation.validator;

import com.sicred.avaliacao.validation.EmailValido;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValido, String> {

    @Override
    public void initialize(EmailValido constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
