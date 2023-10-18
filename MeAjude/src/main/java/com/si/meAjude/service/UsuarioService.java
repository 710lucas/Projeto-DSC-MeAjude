package com.si.meAjude.service;

import com.si.meAjude.service.dtos.usuario.UsuarioDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioSaveDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService{


    public UsuarioDTO save(UsuarioSaveDTO dto);

    public UsuarioDTO getById(Long id);

    public Page<UsuarioDTO> getAll(Pageable pageable);

    public UsuarioDTO update(UsuarioUpdateDTO updateDto);

    public UsuarioDTO logicDelete(Long id);

}
