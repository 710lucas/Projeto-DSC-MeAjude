package com.si.meAjude.service.dtos;

import com.si.meAjude.models.Documento;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.EntityType;

public record DocumentDTO(DocumentType tipoDocumento, String conteudo, EntityType entityType) {
    public DocumentDTO(Documento documento){
        this(documento.getDocumentType(), documento.getConteudo(), documento.getTipoEntidade());
    }
}
