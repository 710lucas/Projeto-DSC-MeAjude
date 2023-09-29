package com.si.meAjude.models.interfaces;

import com.si.meAjude.models.enums.DocumentType;

public interface DocumentValidator {
    void validate(String document);

    DocumentType getDocumentType();
}
