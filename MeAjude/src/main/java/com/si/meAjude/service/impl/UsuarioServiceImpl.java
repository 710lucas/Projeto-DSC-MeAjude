package com.si.meAjude.service.impl;

import com.si.meAjude.models.Usuario;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.validators.CNPJValidator;
import com.si.meAjude.models.validators.CPFValidator;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import com.si.meAjude.repositories.UsuarioRepository;
import com.si.meAjude.service.UsuarioService;
import com.si.meAjude.service.dtos.usuario.UsuarioDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioSaveDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO save(UsuarioSaveDTO dto) {
        DocumentValidator documentValidator = getValidator(dto.documentoDTO().tipoDocumento());
        Usuario usuario = dto.toUsuario();
        usuario.getDocumento().setAndValidateDocument(documentValidator);
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    private DocumentValidator getValidator(DocumentType documentType){
       if(documentType == DocumentType.CPF) return new CPFValidator();
       else if(documentType == DocumentType.CNPJ) return new CNPJValidator();
       else throw new IllegalArgumentException("Tipo de documento inválido");
    }

    @Override
    public Page<UsuarioDTO> getAll(Pageable pageable) {
        return usuarioRepository.findAllByDeletadoFalse(pageable).map(UsuarioDTO::new);
    }

    @Override
    public UsuarioDTO getById(Long id) {
        return new UsuarioDTO(getUsuario(id));
    }

    @Transactional
    @Override
    public UsuarioDTO update(UsuarioUpdateDTO updateDto) {
        Usuario usuarioLocalizado = getUsuario(updateDto.id());
        Usuario usuarioAtualizado = updateUsuario(updateDto, usuarioLocalizado);
        return new UsuarioDTO(usuarioAtualizado);
    }

    private Usuario updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO, Usuario usuario){
        if(usuarioUpdateDTO.nome()!= null && !usuarioUpdateDTO.nome().isBlank()) usuario.setNome(usuarioUpdateDTO.nome());
        if(usuarioUpdateDTO.email()!= null && !usuarioUpdateDTO.email().isBlank()) usuario.setEmail(usuarioUpdateDTO.email());
        if(usuarioUpdateDTO.celular() != null && !usuarioUpdateDTO.celular().isBlank()) usuario.setCelular(usuarioUpdateDTO.celular());
        if(usuarioUpdateDTO.senha() != null && !usuarioUpdateDTO.senha().isBlank()) usuario.setSenha(usuarioUpdateDTO.senha());
        return usuario;
    }

    @Transactional
    @Override
    public UsuarioDTO logicDelete(Long id) {
        Usuario usuarioLocalizado = getUsuario(id);
        usuarioLocalizado.delete();
        return new UsuarioDTO(usuarioLocalizado);
    }

    private Usuario getUsuario(Long id){
        Usuario usuario = usuarioRepository.getById(id);
        if(usuario.isDeletado()) throw new EntityNotFoundException("Unable to find com.si.meAjude.models.Usuario with id "+ id);
        return usuario;
    }

}
