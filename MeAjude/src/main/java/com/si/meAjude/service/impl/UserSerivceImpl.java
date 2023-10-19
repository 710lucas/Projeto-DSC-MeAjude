package com.si.meAjude.service.impl;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.validators.CNPJValidator;
import com.si.meAjude.models.validators.CPFValidator;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.UserSerivce;
import com.si.meAjude.service.dtos.usuario.UserDTO;
import com.si.meAjude.service.dtos.usuario.UserSaveDTO;
import com.si.meAjude.service.dtos.usuario.UserUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserSerivceImpl implements UserSerivce {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO save(UserSaveDTO dto) {
        DocumentValidator documentValidator = getValidator(dto.documentDTO().tipoDocumento());
        User user = dto.toUsuario();
        user.getDocument().setAndValidateDocument(documentValidator);
        return new UserDTO(userRepository.save(user));
    }

    private DocumentValidator getValidator(DocumentType documentType){
       if(documentType == DocumentType.CPF) return new CPFValidator();
       else if(documentType == DocumentType.CNPJ) return new CNPJValidator();
       else throw new IllegalArgumentException("Tipo de documento inválido");
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        return userRepository.findAllByDeletedFalse(pageable).map(UserDTO::new);
    }

    @Override
    public UserDTO getById(Long id) {
        return new UserDTO(getUsuario(id));
    }

    @Transactional
    @Override
    public UserDTO update(UserUpdateDTO updateDto) {
        User userLocalizado = getUsuario(updateDto.id());
        User userAtualizado = updateUsuario(updateDto, userLocalizado);
        return new UserDTO(userAtualizado);
    }

    private User updateUsuario(UserUpdateDTO userUpdateDTO, User user){
        if(userUpdateDTO.nome()!= null && !userUpdateDTO.nome().isBlank()) user.setName(userUpdateDTO.nome());
        if(userUpdateDTO.email()!= null && !userUpdateDTO.email().isBlank()) user.setEmail(userUpdateDTO.email());
        if(userUpdateDTO.celular() != null && !userUpdateDTO.celular().isBlank()) user.setPhone(userUpdateDTO.celular());
        if(userUpdateDTO.senha() != null && !userUpdateDTO.senha().isBlank()) user.setPassword(userUpdateDTO.senha());
        return user;
    }

    @Transactional
    @Override
    public UserDTO logicDelete(Long id) {
        User userLocalizado = getUsuario(id);
        userLocalizado.setDeleted(true);
        return new UserDTO(userLocalizado);
    }

    private User getUsuario(Long id){
        User user = userRepository.getById(id);
        if(user.isDeleted()) throw new EntityNotFoundException("Unable to find com.si.meAjude.models.Usuario with id "+ id);
        return user;
    }

}
