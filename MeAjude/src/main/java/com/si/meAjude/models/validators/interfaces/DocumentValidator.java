package com.si.meAjude.models.validators.interfaces;

import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.DocumentEntityType;

public interface DocumentValidator {
    void validate(String document, DocumentEntityType documentEntityType);

    DocumentType getDocumentType();
}
