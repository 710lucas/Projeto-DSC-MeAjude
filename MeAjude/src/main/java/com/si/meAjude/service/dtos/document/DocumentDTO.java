package com.si.meAjude.service.dtos.document;

import com.si.meAjude.models.Document;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.DocumentEntityType;

public record DocumentDTO(

        DocumentType documentType,

        String content,

        DocumentEntityType documentEntityType

) {
    public DocumentDTO(Document document){
        this(document.getDocumentType(), document.getContent(), document.getDocumentEntityType());
    }

    public Document toDocument(){
        Document document = new Document();
        document.setDocumentType(documentType());
        document.setContent(content());
        document.setDocumentEntityType(documentEntityType());
        return document;
    }
}
