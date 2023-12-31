package com.si.meAjude.service.impl;

import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.enums.UserRole;
import com.si.meAjude.models.validators.CNPJValidator;
import com.si.meAjude.models.validators.CPFValidator;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.UserSerivce;
import com.si.meAjude.service.dtos.user.UserDTO;
import com.si.meAjude.service.dtos.user.UserSaveDTO;
import com.si.meAjude.service.dtos.user.UserUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserServiceImpl implements UserSerivce {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO save(UserSaveDTO dto) {
        User user = dto.toUser();
        DocumentValidator documentValidator = getValidator(dto.documentDTO().documentType());
        user.getDocument().setAndValidateDocument(documentValidator);
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        user.setPassword(encryptedPassword);
        return new UserDTO(userRepository.save(user));
    }

    private DocumentValidator getValidator(DocumentType documentType){
       if(documentType == DocumentType.CPF) return new CPFValidator();
       else if(documentType == DocumentType.CNPJ) return new CNPJValidator();
       else throw new IllegalArgumentException("Invalid document type: '"+ documentType + "'");
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        return userRepository.findAllByDeletedFalse(pageable).map(UserDTO::new);
    }

    @Override
    public UserDTO getById(Long id) {
        return new UserDTO(getUser(id));
    }

    @Transactional
    @Override
    public UserDTO update(UserUpdateDTO updateDto, Long id) {
        User userLocated = getUser(id);
        User userUpdated = updateUser(updateDto, userLocated);
        return new UserDTO(userUpdated);
    }

    private User updateUser(UserUpdateDTO userUpdateDTO, User user){
        if(userUpdateDTO.name()!= null && !userUpdateDTO.name().isBlank()) user.setName(userUpdateDTO.name());
        if(userUpdateDTO.phone() != null && !userUpdateDTO.phone().isBlank()) user.setPhone(userUpdateDTO.phone());
        if(userUpdateDTO.email() != null && !userUpdateDTO.email().isBlank()) user.setEmail(userUpdateDTO.email());
        if(userUpdateDTO.password() != null && !userUpdateDTO.password().isBlank()) user.setPassword(userUpdateDTO.password());
        return user;
    }

    @Transactional
    @Override
    public UserDTO logicDelete(Long id) {
        User userLocated = getUser(id);
        userLocated.setDeleted(true);
        return new UserDTO(userLocated);
    }

    private User getUser(Long id){
        User user = userRepository.getById(id);
        if(user.isDeleted()) throw new EntityNotFoundException("Unable to find User with id "+ id);
        return user;
    }
}
