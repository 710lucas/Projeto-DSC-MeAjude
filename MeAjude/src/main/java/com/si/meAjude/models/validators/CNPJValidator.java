package com.si.meAjude.models.validators;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.EntityType;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import org.springframework.stereotype.Component;

@Component
public class CNPJValidator implements DocumentValidator {

    public static void throwExceptionIfInvalido(String cnpj, EntityType entityType) {
        if (entityType == EntityType.PESSOA_FISICA) {
            throw new IllegalArgumentException("CNPJ incompátivel para o tipo entidade informado: " + entityType);
        }
        // Remova qualquer formatação do CNPJ
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifique se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter 14 dígitos");
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) {
            primeiroDigito = 0;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) {
            segundoDigito = 0;
        }

        // Verifica se os dígitos calculados são iguais aos dígitos no CNPJ
        if (primeiroDigito != Character.getNumericValue(cnpj.charAt(12)) || segundoDigito != Character.getNumericValue(cnpj.charAt(13))) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }

    @Override
    public void validate(String document, EntityType entityType) {
        throwExceptionIfInvalido(document, entityType);
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CNPJ;
    }
}
