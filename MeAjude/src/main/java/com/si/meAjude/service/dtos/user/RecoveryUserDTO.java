package com.si.meAjude.service.dtos.user;

import com.si.meAjude.models.Role;

import java.util.List;

public record RecoveryUserDTO(Long id, String email, List<Role> roles) {
}
