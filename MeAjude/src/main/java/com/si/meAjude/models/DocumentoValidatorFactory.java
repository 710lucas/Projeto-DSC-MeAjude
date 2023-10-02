package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DocumentoValidatorFactory {

    private Map<DocumentType, DocumentValidator> documentValidators = new HashMap<>();

    @Autowired
    public DocumentoValidatorFactory(Set<DocumentValidator> documentValidators) {
        createDocumentValidators(documentValidators);
    }

    private void createDocumentValidators(Set<DocumentValidator> documentValidatorsSet) {
        documentValidatorsSet.forEach(documentValidator -> {
            documentValidators.put(documentValidator.getDocumentType(), documentValidator);
        });
    }

    public DocumentValidator getValidator(DocumentType documentType) {
        return documentValidators.get(documentType);
    }



}
