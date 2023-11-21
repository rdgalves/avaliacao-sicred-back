package com.sicred.avaliacao.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DataInicioValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataInicioValida {

    String message() default "A data e hora de início não podem ser anteriores à data atual.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
