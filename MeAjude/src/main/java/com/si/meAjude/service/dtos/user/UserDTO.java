package com.si.meAjude.service.dtos.user;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.UserRole;
import com.si.meAjude.service.dtos.document.DocumentDTO;

public record UserDTO(
        Long id,
        String email,
        String name,
        String phone,
        boolean deleted,
        DocumentDTO documentDTO,
        UserRole role) {

    public UserDTO(User user){
        this(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.isDeleted(), new DocumentDTO(user.getDocument()), user.getRole());
    }
}
