package com.si.meAjude.service.dtos.user;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import com.si.meAjude.service.dtos.document.DocumentDTO;

public record UserDTO(
        Long id,
        String email
) {

    public UserDTO(User user){
        this(user.getId(), user.getEmail());
    }
}
