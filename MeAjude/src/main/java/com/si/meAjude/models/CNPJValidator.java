package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.interfaces.DocumentValidator;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.stereotype.Component;

@Component
public class CNPJValidator implements DocumentValidator {

    public static boolean isValid(String cnpj) {
        // Remova qualquer formatação do CNPJ
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifique se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
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
        return Character.getNumericValue(cnpj.charAt(12)) == primeiroDigito && Character.getNumericValue(cnpj.charAt(13)) == segundoDigito;
    }

    @Override
    public void validate(String document) {
        if(!isValid(document))
            throw new IllegalArgumentException("CNPJ inválido");
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CNPJ;
    }
}
