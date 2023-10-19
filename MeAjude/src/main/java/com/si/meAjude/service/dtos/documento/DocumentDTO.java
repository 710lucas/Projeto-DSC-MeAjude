package com.si.meAjude.service.dtos.documento;

import com.si.meAjude.models.Document;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.DocumentEntityType;

public record DocumentDTO(

        DocumentType tipoDocumento,

        String conteudo,

        DocumentEntityType tipoEntidade

) {
    public DocumentDTO(Document document){
        this(document.getDocumentType(), document.getContent(), document.getDocumentEntityType());
    }

    public Document toDocumento(){
        Document document = new Document();
        document.setDocumentType(tipoDocumento());
        document.setContent(conteudo());
        document.setDocumentEntityType(tipoEntidade());
        return document;
    }
}
