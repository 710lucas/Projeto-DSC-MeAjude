package com.si.meAjude.service.dtos.user;

import com.si.meAjude.models.enums.RoleName;

public record CreateUserDTO(String email, String password, RoleName role) {
}
