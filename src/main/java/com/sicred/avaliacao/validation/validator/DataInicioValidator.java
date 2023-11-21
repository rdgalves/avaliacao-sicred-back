package com.sicred.avaliacao.validation.validator;

import com.sicred.avaliacao.validation.DataInicioValida;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DataInicioValidator implements ConstraintValidator<DataInicioValida, LocalDateTime> {

    @Override
    public void initialize(DataInicioValida constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDateTime inicio, ConstraintValidatorContext constraintValidatorContext) {
        return inicio == null || inicio.isAfter(LocalDateTime.now());
    }
}

