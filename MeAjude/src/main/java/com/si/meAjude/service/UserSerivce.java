package com.si.meAjude.service;

import com.si.meAjude.service.dtos.user.UserDTO;
import com.si.meAjude.service.dtos.user.UserSaveDTO;
import com.si.meAjude.service.dtos.user.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserSerivce {


     UserDTO save(UserSaveDTO dto);

     UserDTO getById(Long id);

     Page<UserDTO> getAll(Pageable pageable);

     UserDTO update(UserUpdateDTO updateDto);

     UserDTO logicDelete(Long id);
}
