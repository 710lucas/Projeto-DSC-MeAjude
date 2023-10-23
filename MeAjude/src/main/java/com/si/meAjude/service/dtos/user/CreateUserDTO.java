package com.si.meAjude.service.dtos.user;

import com.si.meAjude.models.enums.UserRole;

public record CreateUserDTO(String email, String password, UserRole role) {
}
