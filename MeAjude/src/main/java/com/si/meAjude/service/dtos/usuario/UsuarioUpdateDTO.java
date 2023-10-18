package com.si.meAjude.service.dtos.usuario;

import com.si.meAjude.models.Usuario;
import com.si.meAjude.service.dtos.documento.DocumentoDTO;
import jakarta.validation.constraints.NotNull;


public record UsuarioUpdateDTO(
        @NotNull Long id,
        String email,
        String nome,
        String celular,
        String senha,
        DocumentoDTO documentoDTO){

    public UsuarioUpdateDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getEmail(), usuario.getNome(), usuario.getCelular(), usuario.getSenha(), new DocumentoDTO(usuario.getDocumento()));
    }
}