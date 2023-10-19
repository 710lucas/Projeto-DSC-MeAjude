package com.si.meAjude.service.dtos.usuario;

import com.si.meAjude.models.User;
import com.si.meAjude.service.dtos.documento.DocumentDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UserSaveDTO(

        @NotBlank
        String name,

        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        String password,

        @NotNull
        DocumentDTO documentDTO

) {
    public UserSaveDTO(User user){
        this(user.getName(), user.getEmail(), user.getPassword(), user.getPhone(), new DocumentDTO(user.getDocument()));
    }

    public User toUsuario(){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setDocument(documentDTO.toDocumento());
        return user;
    }

}
