package com.si.meAjude.service.dtos.usuario;

import com.si.meAjude.models.User;
import com.si.meAjude.service.dtos.documento.DocumentDTO;

public record UserDTO(
        Long id,
        String name,
        String email,
        String phone,
        boolean deleted,
        DocumentDTO documentDTO) {

    public UserDTO(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.isDeleted(), new DocumentDTO(user.getDocument()));
    }
}