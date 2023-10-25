package com.si.meAjude.service.dtos.user;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import jakarta.validation.constraints.NotNull;


public record UserUpdateDTO(
        String name,
        String phone,
        String email,
        String password,
        UserRole role
    ){

    public UserUpdateDTO(User user) {
        this(user.getName(), user.getPhone(), user.getEmail(), user.getPassword(), user.getRole());
    }
}