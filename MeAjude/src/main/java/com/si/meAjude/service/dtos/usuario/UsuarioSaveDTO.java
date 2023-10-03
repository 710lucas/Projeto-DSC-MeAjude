package com.si.meAjude.service.dtos.usuario;

import com.si.meAjude.models.Usuario;
import com.si.meAjude.service.dtos.documento.DocumentoDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UsuarioSaveDTO(

        @NotBlank
        String nome,

        @Email
        String email,

        @NotBlank
        String senha,

        @NotBlank
        String celular,

        @NotNull
        DocumentoDTO documentoDTO

) {
    public UsuarioSaveDTO(Usuario usuario){
        this(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCelular(), new DocumentoDTO(usuario.getDocumento()));
    }

    public Usuario toUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCelular(celular);
        usuario.setDocumento(documentoDTO.toDocumento());
        return usuario;
    }

}
