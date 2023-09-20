package com.si.meAjude.service.impl;

import com.si.meAjude.models.Usuario;
import com.si.meAjude.repositories.UsuarioRepository;
import com.si.meAjude.service.UsuarioService;
import com.si.meAjude.service.dtos.UsuarioDTO;
import com.si.meAjude.service.dtos.UsuarioUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<UsuarioDTO> getAll() {
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).toList();
    }

    @Override
    public UsuarioDTO getById(Long id) {
        return new UsuarioDTO(usuarioRepository.getById(id));
    }

    @Transactional
    @Override
    public UsuarioDTO update(UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuarioLocalizado = usuarioRepository.getById(usuarioUpdateDTO.getId());
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

    @Override
    public List<UsuarioDTO> getAllByDeletedFalse() {
        return usuarioRepository.findAllByDeletadoFalse().stream().map(UsuarioDTO::new).toList();
    }
}
