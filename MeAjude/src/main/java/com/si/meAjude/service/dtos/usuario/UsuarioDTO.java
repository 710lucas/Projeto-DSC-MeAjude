package com.si.meAjude.service.dtos.usuario;

import com.si.meAjude.models.Usuario;
import com.si.meAjude.service.dtos.documento.DocumentDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        Long id,

        @NotBlank
        String nome,

        @Email
        String email,

        @NotBlank
        String celular,

        @NotNull
        DocumentDTO documentDTO,

        boolean deletado
) {
    public UsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCelular(), new DocumentDTO(usuario.getDocumento()), usuario.isDeletado());
    }
}
