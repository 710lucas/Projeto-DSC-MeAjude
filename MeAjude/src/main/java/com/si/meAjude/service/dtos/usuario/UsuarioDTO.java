package com.si.meAjude.service.dtos.usuario;

import com.si.meAjude.models.Usuario;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.service.dtos.documento.DocumentoDTO;

public record UsuarioDTO(
        Long id,
        String nome,
        String email,
        String celular,
        boolean deletado,
        DocumentoDTO documento) {

    public UsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCelular(), usuario.isDeletado(), new DocumentoDTO(usuario.getDocumento()));
    }
}
