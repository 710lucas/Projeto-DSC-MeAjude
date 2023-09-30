package com.si.meAjude.service.dtos;

import com.si.meAjude.models.Documento;
import com.si.meAjude.models.DocumentoValidatorFactory;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.models.enums.EntityType;
import com.si.meAjude.models.interfaces.DocumentValidator;
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
