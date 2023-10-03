package com.si.meAjude.service.dtos.usuario;

import com.si.meAjude.models.Usuario;
import com.si.meAjude.models.enums.DocumentType;

public record UsuarioDTO(
        Long id,
        String nome,
        String email,
        String celular,
        String conteudoDocumento,
        DocumentType tipoDocumento) {

    public UsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCelular(), usuario.getDocumento().getConteudo(), usuario.getDocumento().getDocumentType());
    }
}
