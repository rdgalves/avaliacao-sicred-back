package com.sicred.avaliacao.validation.validator;

import com.sicred.avaliacao.validation.CpfValido;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<CpfValido, String> {

    @Override
    public void initialize(CpfValido constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.length() != 11 || isCpfInvalido(cpf)) {
            return false;
        }

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        return validaDigitos(cpf);
    }

    private boolean isCpfInvalido(String cpf) {
        // Verifica se o CPF possui todos os dígitos iguais, o que o tornaria inválido
        return cpf.chars().allMatch(c -> c == cpf.charAt(0));
    }

    private boolean validaDigitos(String cpf) {
        int peso = 10;
        int soma = 0;
        int numero;
        int digito1, digito2;

        // Calcula o primeiro dígito verificador
        for (int i = 0; i < 9; i++) {
            numero = (cpf.charAt(i) - '0');
            soma += (numero * peso);
            peso--;
        }
        int resto = 11 - (soma % 11);
        digito1 = (resto > 9) ? 0 : resto;

        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            numero = (cpf.charAt(i) - '0');
            soma += (numero * peso);
            peso--;
        }
        resto = 11 - (soma % 11);
        digito2 = (resto > 9) ? 0 : resto;

        // Verifica se os dígitos calculados correspondem aos dígitos informados
        return (digito1 == cpf.charAt(9) - '0' && digito2 == cpf.charAt(10) - '0');
    }
}
