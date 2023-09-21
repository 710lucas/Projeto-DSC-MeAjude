package com.si.meAjude.service.dtos;

import com.si.meAjude.models.Documento;
import com.si.meAjude.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public record UsuarioUpdateDTO(@NotNull Long id, String email, String nome, String celular, String senha, Documento documento){

    public UsuarioUpdateDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getEmail(), usuario.getNome(), usuario.getCelular(), usuario.getSenha(), usuario.getDocumento());
    }

    public Usuario updateUsuario(Usuario usuario){
        return updateUsuario(this, usuario);
    }

    public static Usuario updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO, Usuario usuario){
        if(usuarioUpdateDTO.nome()!= null && !usuarioUpdateDTO.nome().isBlank()) usuario.setNome(usuarioUpdateDTO.nome());
        if(usuarioUpdateDTO.email()!= null && !usuarioUpdateDTO.email().isBlank()) usuario.setEmail(usuarioUpdateDTO.email());
        if(usuarioUpdateDTO.celular() != null && !usuarioUpdateDTO.celular.isBlank()) usuario.setCelular(usuarioUpdateDTO.celular());
        if(usuarioUpdateDTO.documento()!= null) usuario.setDocumento(usuarioUpdateDTO.documento());
        if(usuarioUpdateDTO.senha() != null && !usuarioUpdateDTO.senha().isBlank()) usuario.setSenha(usuarioUpdateDTO.senha());
        return usuario;
    }
}