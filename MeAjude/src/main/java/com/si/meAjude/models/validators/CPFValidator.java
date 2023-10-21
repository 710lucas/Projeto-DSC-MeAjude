package com.si.meAjude.models.validators;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.DocumentEntityType;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import org.springframework.stereotype.Component;

@Component
public class CPFValidator implements DocumentValidator {

    public void throwExceptionIfInvalid(String cpf, DocumentEntityType documentEntityType) {
        if (documentEntityType != DocumentEntityType.INDIVIDUAL)
            throw new IllegalArgumentException("CPF is incompatible with the specified entity type: " + documentEntityType);

        // Remove any formatting from the CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Check if the CPF has 11 digits
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF must contain 11 digits");
        }

        // Calculate the first verification digit
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        // Calculate the second verification digit
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) {
            secondDigit = 0;
        }

        // Check if the calculated digits match the digits in the CPF
        if (firstDigit != Character.getNumericValue(cpf.charAt(9)) || secondDigit != Character.getNumericValue(cpf.charAt(10))) {
            throw new IllegalArgumentException("Invalid CPF");
        }
    }


    @Override
    public void validate(String document, DocumentEntityType documentEntityType) {
        throwExceptionIfInvalid(document, documentEntityType);
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CPF;
    }
}
