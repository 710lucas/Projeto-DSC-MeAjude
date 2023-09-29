package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.interfaces.DocumentValidator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

@Component
public class CPFValidator implements DocumentValidator {

    @CPF
    private String cpf;

    @Override
    public void validate(String document) {
        this.cpf = cpf;
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CPF;
    }
}
