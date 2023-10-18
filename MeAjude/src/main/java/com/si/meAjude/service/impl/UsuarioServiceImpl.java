package com.si.meAjude.service.impl;

import com.si.meAjude.models.DocumentoValidatorFactory;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import com.si.meAjude.repositories.UsuarioRepository;
import com.si.meAjude.service.UsuarioService;
import com.si.meAjude.service.dtos.usuario.UsuarioDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioSaveDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private DocumentoValidatorFactory documentoValidatorFactory;

    @Override
    public UsuarioDTO save(UsuarioSaveDTO dto) {
        DocumentValidator documentValidator = documentoValidatorFactory.getValidator(dto.documentoDTO().tipoDocumento());
        Usuario usuario = dto.toUsuario();
        usuario.getDocumento().setAndValidateDocument(documentValidator);
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
    public UsuarioDTO update(UsuarioUpdateDTO updateDto) {
        Usuario usuarioLocalizado = usuarioRepository.getById(updateDto.id());
        Usuario usuarioAtualizado = updateUsuario(updateDto, usuarioLocalizado);
        return new UsuarioDTO(usuarioAtualizado);
    }

    private Usuario updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO, Usuario usuario){
        if(usuarioUpdateDTO.nome()!= null && !usuarioUpdateDTO.nome().isBlank()) usuario.setNome(usuarioUpdateDTO.nome());
        if(usuarioUpdateDTO.email()!= null && !usuarioUpdateDTO.email().isBlank()) usuario.setEmail(usuarioUpdateDTO.email());
        if(usuarioUpdateDTO.celular() != null && !usuarioUpdateDTO.celular().isBlank()) usuario.setCelular(usuarioUpdateDTO.celular());
        if(usuarioUpdateDTO.documentoDTO()!= null) usuario.setDocumento(usuarioUpdateDTO.documentoDTO().toDocumento());
        if(usuarioUpdateDTO.senha() != null && !usuarioUpdateDTO.senha().isBlank()) usuario.setSenha(usuarioUpdateDTO.senha());
        return usuario;
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
