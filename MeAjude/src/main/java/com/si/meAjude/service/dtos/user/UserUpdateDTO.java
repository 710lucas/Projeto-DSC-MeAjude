package com.si.meAjude.service.dtos.user;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import jakarta.validation.constraints.NotNull;


public record UserUpdateDTO(
        @NotNull Long id,
        String name,
        String phone,
        String email,
        String password,
        UserRole role
    ){

    public UserUpdateDTO(User user) {
        this(user.getId(), user.getName(), user.getPhone(), user.getEmail(), user.getPassword(), user.getRole());
    }
}