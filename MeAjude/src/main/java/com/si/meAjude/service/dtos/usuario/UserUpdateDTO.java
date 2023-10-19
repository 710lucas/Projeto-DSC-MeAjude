package com.si.meAjude.service.dtos.usuario;

import com.si.meAjude.models.User;
import jakarta.validation.constraints.NotNull;


public record UserUpdateDTO(
        @NotNull Long id,
        String name,
        String phone,
        String password){

    public UserUpdateDTO(User user) {
        this(user.getId(), user.getName(), user.getPhone(), user.getPassword());
    }
}