package com.si.meAjude.service;

import com.si.meAjude.service.dtos.user.UserDTO;
import com.si.meAjude.service.dtos.user.UserSaveDTO;
import com.si.meAjude.service.dtos.user.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface UserSerivce {


     UserDTO save(UserSaveDTO dto);

     UserDTO getById(Long id);

     Page<UserDTO> getAll(Pageable pageable);

     UserDTO update(UserUpdateDTO updateDto, Long id);

     UserDTO logicDelete(Long id);
}
