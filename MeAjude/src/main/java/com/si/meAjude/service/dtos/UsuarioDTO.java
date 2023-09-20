package com.si.meAjude.service.dtos;

import com.si.meAjude.models.Documento;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.models.enums.EntidadeEnum;

public record UsuarioDTO(Long id, String nome, String email, String celular, Documento documento, EntidadeEnum entidade) {
    public UsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCelular(), usuario.getDocumento(), usuario.getTipoEntidade());
    }
}
