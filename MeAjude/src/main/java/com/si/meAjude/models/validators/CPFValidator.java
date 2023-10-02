package com.si.meAjude.models.validators;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.EntityType;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import org.springframework.stereotype.Component;

@Component
public class CPFValidator implements DocumentValidator {

    public void throwExceptionIfInvalid(String cpf, EntityType entityType) {
        if(entityType != EntityType.PESSOA_FISICA) throw new IllegalArgumentException("CPF incompatível para o tipo entidade informado: " + entityType);
        // Remova qualquer formatação do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifique se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos");
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) {
            primeiroDigito = 0;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) {
            segundoDigito = 0;
        }

        // Verifica se os dígitos calculados são iguais aos dígitos no CPF
        if (primeiroDigito != Character.getNumericValue(cpf.charAt(9)) || segundoDigito != Character.getNumericValue(cpf.charAt(10))) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    @Override
    public void validate(String document, EntityType entityType) {
        throwExceptionIfInvalid(document, entityType);
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CPF;
    }
}
