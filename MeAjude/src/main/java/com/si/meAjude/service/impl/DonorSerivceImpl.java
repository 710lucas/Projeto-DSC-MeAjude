package com.si.meAjude.service.impl;

import com.si.meAjude.models.Donor;
import com.si.meAjude.models.enums.DocumentType;
import com.si.meAjude.models.validators.CNPJValidator;
import com.si.meAjude.models.validators.CPFValidator;
import com.si.meAjude.models.validators.interfaces.DocumentValidator;
import com.si.meAjude.repositories.DonorRepository;
import com.si.meAjude.service.DonorSerivce;
import com.si.meAjude.service.dtos.donor.DonorDTO;
import com.si.meAjude.service.dtos.donor.DonorSaveDTO;
import com.si.meAjude.service.dtos.donor.DonorUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DonorSerivceImpl implements DonorSerivce {

    @Autowired
    DonorRepository donorRepository;

    @Override
    public DonorDTO save(DonorSaveDTO dto) {
        DocumentValidator documentValidator = getValidator(dto.documentDTO().documentType());
        Donor donor = dto.toUser();
        donor.getDocument().setAndValidateDocument(documentValidator);
        return new DonorDTO(donorRepository.save(donor));
    }

    private DocumentValidator getValidator(DocumentType documentType){
       if(documentType == DocumentType.CPF) return new CPFValidator();
       else if(documentType == DocumentType.CNPJ) return new CNPJValidator();
       else throw new IllegalArgumentException("Invalid document type: '"+ documentType + "'");
    }

    @Override
    public Page<DonorDTO> getAll(Pageable pageable) {
        return donorRepository.findAllByDeletedFalse(pageable).map(DonorDTO::new);
    }

    @Override
    public DonorDTO getById(Long id) {
        return new DonorDTO(getUsuario(id));
    }

    @Transactional
    @Override
    public DonorDTO update(DonorUpdateDTO updateDto) {
        Donor donorLocated = getUsuario(updateDto.id());
        Donor donorUpdated = updateUsuario(updateDto, donorLocated);
        return new DonorDTO(donorUpdated);
    }

    private Donor updateUsuario(DonorUpdateDTO donorUpdateDTO, Donor donor){
        if(donorUpdateDTO.name()!= null && !donorUpdateDTO.name().isBlank()) donor.setName(donorUpdateDTO.name());
        if(donorUpdateDTO.phone() != null && !donorUpdateDTO.phone().isBlank()) donor.setPhone(donorUpdateDTO.phone());
        return donor;
    }

    @Transactional
    @Override
    public DonorDTO logicDelete(Long id) {
        Donor donorLocalizado = getUsuario(id);
        donorLocalizado.setDeleted(true);
        return new DonorDTO(donorLocalizado);
    }

    private Donor getUsuario(Long id){
        Donor donor = donorRepository.getById(id);
        if(donor.isDeleted()) throw new EntityNotFoundException("Unable to find Donor with id "+ id);
        return donor;
    }

}
