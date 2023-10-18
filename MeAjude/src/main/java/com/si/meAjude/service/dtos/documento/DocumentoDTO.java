package com.si.meAjude.service.dtos.documento;

import com.si.meAjude.models.Documento;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.EntityType;

public record DocumentoDTO(

        DocumentType tipoDocumento,

        String conteudo,

        EntityType tipoEntidade

) {
    public DocumentoDTO(Documento documento){
        this(documento.getDocumentType(), documento.getConteudo(), documento.getTipoEntidade());
    }

    public Documento toDocumento(){
        Documento documento = new Documento();
        documento.setDocumentType(tipoDocumento());
        documento.setConteudo(conteudo());
        documento.setTipoEntidade(tipoEntidade());
        return documento;
    }
}
