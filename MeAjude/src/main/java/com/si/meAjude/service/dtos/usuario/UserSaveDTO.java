package com.si.meAjude.service.dtos.usuario;

import com.si.meAjude.models.User;
import com.si.meAjude.service.dtos.documento.DocumentDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UserSaveDTO(

        @NotBlank
        String nome,

        @Email
        String email,

        @NotBlank
        String celular,

        @NotBlank
        String senha,

        @NotNull
        DocumentDTO documentDTO

) {
    public UserSaveDTO(User user){
        this(user.getName(), user.getEmail(), user.getPassword(), user.getPhone(), new DocumentDTO(user.getDocument()));
    }

    public User toUsuario(){
        User user = new User();
        user.setName(nome);
        user.setPassword(senha);
        user.setEmail(email);
        user.setPhone(celular);
        user.setDocument(documentDTO.toDocumento());
        return user;
    }

}
