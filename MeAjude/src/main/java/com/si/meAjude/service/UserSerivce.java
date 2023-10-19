package com.si.meAjude.service;

import com.si.meAjude.service.dtos.usuario.UserDTO;
import com.si.meAjude.service.dtos.usuario.UserSaveDTO;
import com.si.meAjude.service.dtos.usuario.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserSerivce {


    public UserDTO save(UserSaveDTO dto);

    public UserDTO getById(Long id);

    public Page<UserDTO> getAll(Pageable pageable);

    public UserDTO update(UserUpdateDTO updateDto);

    public UserDTO logicDelete(Long id);

}
