package com.si.meAjude.service;

import com.si.meAjude.models.User;
import com.si.meAjude.service.dtos.usuario.UserDTO;
import com.si.meAjude.service.dtos.usuario.UserSaveDTO;
import com.si.meAjude.service.dtos.usuario.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserSerivce {


     UserDTO save(UserSaveDTO dto);

     UserDTO getById(Long id);

     Page<UserDTO> getAll(Pageable pageable);

     UserDTO update(UserUpdateDTO updateDto);

     UserDTO logicDelete(Long id);
}
