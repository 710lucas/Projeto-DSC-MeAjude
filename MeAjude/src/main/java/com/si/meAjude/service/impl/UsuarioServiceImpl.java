package com.si.meAjude.service.impl;

import com.si.meAjude.models.Usuario;
import com.si.meAjude.repositories.UsuarioRepository;
import com.si.meAjude.service.UsuarioService;
import com.si.meAjude.service.dtos.UsuarioDTO;
import com.si.meAjude.service.dtos.UsuarioUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO save(Usuario usuario) {
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public Page<UsuarioDTO> getAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(UsuarioDTO::new);
    }

    @Override
    public Page<UsuarioDTO> getAllByDeletedFalse(Pageable pageable) {
        return usuarioRepository.findAllByDeletadoFalse(pageable).map(UsuarioDTO::new);
    }

    @Override
    public UsuarioDTO getById(Long id) {
        return new UsuarioDTO(usuarioRepository.getById(id));
    }

    @Transactional
    @Override
    public UsuarioDTO update(UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuarioLocalizado = usuarioRepository.getById(usuarioUpdateDTO.id());
        Usuario usuarioAtualizado = usuarioUpdateDTO.updateUsuario(usuarioLocalizado);
        return new UsuarioDTO(usuarioAtualizado);
    }

    @Override
    public UsuarioDTO delete(Long id) {
        Usuario usuarioLocalizado = usuarioRepository.getById(id);
        usuarioRepository.delete(usuarioLocalizado);
        return new UsuarioDTO(usuarioLocalizado);
    }

    @Transactional
    @Override
    public UsuarioDTO logicDelete(Long id) {
        Usuario usuarioLocalizado = usuarioRepository.getById(id);
        usuarioLocalizado.delete();
        return new UsuarioDTO(usuarioLocalizado);
    }

}
