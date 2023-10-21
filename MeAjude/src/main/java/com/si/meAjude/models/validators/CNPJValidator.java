package com.si.meAjude.models.validators;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.DocumentEntityType;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import org.springframework.stereotype.Component;

@Component
public class CNPJValidator implements DocumentValidator {

    public static void throwExceptionIfInvalid(String cnpj, DocumentEntityType documentEntityType) {
        if (documentEntityType == DocumentEntityType.INDIVIDUAL) {
            throw new IllegalArgumentException("CNPJ is incompatible with the specified entity type: " + documentEntityType);
        }

        // Remove any formatting from the CNPJ
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Check if the CNPJ has 14 digits
        if (cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ must contain 14 digits");
        }

        // Calculate the first verification digit
        int sum = 0;
        int weight = 2;
        for (int i = 11; i >= 0; i--) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weight;
            weight++;
            if (weight == 10) {
                weight = 2;
            }
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        // Calculate the second verification digit
        sum = 0;
        weight = 2;
        for (int i = 12; i >= 0; i--) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weight;
            weight++;
            if (weight == 10) {
                weight = 2;
            }
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) {
            secondDigit = 0;
        }

        // Check if the calculated digits match the digits in the CNPJ
        if (firstDigit != Character.getNumericValue(cnpj.charAt(12)) || secondDigit != Character.getNumericValue(cnpj.charAt(13))) {
            throw new IllegalArgumentException("Invalid CNPJ");
        }
    }

    @Override
    public void validate(String document, DocumentEntityType documentEntityType) {
        throwExceptionIfInvalid(document, documentEntityType);
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CNPJ;
    }
}
