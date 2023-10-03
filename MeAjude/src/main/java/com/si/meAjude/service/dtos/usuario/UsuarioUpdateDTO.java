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

    public Usuario updateUsuario(Usuario usuario){
        return updateUsuario(this, usuario);
    }

    private Usuario updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO, Usuario usuario){
        if(usuarioUpdateDTO.nome()!= null && !usuarioUpdateDTO.nome().isBlank()) usuario.setNome(usuarioUpdateDTO.nome());
        if(usuarioUpdateDTO.email()!= null && !usuarioUpdateDTO.email().isBlank()) usuario.setEmail(usuarioUpdateDTO.email());
        if(usuarioUpdateDTO.celular() != null && !usuarioUpdateDTO.celular.isBlank()) usuario.setCelular(usuarioUpdateDTO.celular());
        if(usuarioUpdateDTO.documentoDTO()!= null) usuario.setDocumento(usuarioUpdateDTO.documentoDTO().toDocumento());
        if(usuarioUpdateDTO.senha() != null && !usuarioUpdateDTO.senha().isBlank()) usuario.setSenha(usuarioUpdateDTO.senha());
        return usuario;
    }
}