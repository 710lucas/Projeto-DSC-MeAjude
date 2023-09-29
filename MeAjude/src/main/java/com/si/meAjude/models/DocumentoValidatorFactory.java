package com.si.meAjude.models;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.interfaces.DocumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DocumentoValidatorFactory {

    private Map<DocumentType, DocumentValidator> documentValidators;

    @Autowired
    public DocumentoValidatorFactory(Set<DocumentValidator> documentValidators) {
        createDocumentValidators(documentValidators);
    }

    public DocumentValidator getValidator(DocumentType documentType) {
        return documentValidators.get(documentType);
    }

    private void createDocumentValidators(Set<DocumentValidator> documentValidatorsSet) {
        documentValidators = new HashMap<DocumentType, DocumentValidator>();
        documentValidatorsSet.forEach(documentValidator -> {
            documentValidators.put(documentValidator.getDocumentType(), documentValidator);
        });
    }



}
