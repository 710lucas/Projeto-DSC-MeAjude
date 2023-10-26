package com.si.meAjude.service.dtos.user;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import com.si.meAjude.service.dtos.document.DocumentDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UserSaveDTO(

        @Email
        String email,

        String password,

        @NotBlank
        String name,

        @NotBlank
        String phone,

        @NotNull
        DocumentDTO documentDTO,

        @NotNull
        @Enumerated(EnumType.STRING)
        UserRole role

) {
    public UserSaveDTO(User user){
        this(user.getEmail(), user.getPassword(), user.getName(), user.getPhone(), new DocumentDTO(user.getDocument()), user.getRole());
    }

    public User toUser(){
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setDocument(documentDTO.toDocument());
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

}
