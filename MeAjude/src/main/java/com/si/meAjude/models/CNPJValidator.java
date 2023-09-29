package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.interfaces.DocumentValidator;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.stereotype.Component;

@Component
public class CNPJValidator implements DocumentValidator {

    @CNPJ
    private String cnpj;

    @Override
    public void validate(String document) {
        this.cnpj = cnpj;
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CNPJ;
    }
}
