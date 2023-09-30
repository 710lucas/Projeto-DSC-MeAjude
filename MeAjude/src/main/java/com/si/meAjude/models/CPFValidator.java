package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.interfaces.DocumentValidator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

@Component
public class CPFValidator implements DocumentValidator {

    public static boolean isValid(String cpf) {
        // Remova qualquer formatação do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifique se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
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
        return Character.getNumericValue(cpf.charAt(9)) == primeiroDigito && Character.getNumericValue(cpf.charAt(10)) == segundoDigito;
    }

    @Override
    public void validate(String document) {
        if(!isValid(document))
            throw new IllegalArgumentException("CPF inválido");
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CPF;
    }
}
