package com.si.meAjude.service.dtos.donor;

import com.si.meAjude.models.Donor;
import com.si.meAjude.service.dtos.documento.DocumentDTO;

public record DonorDTO(
        Long id,
        String name,
        String phone,
        boolean deleted,
        DocumentDTO documentDTO) {

    public DonorDTO(Donor donor){
        this(donor.getId(), donor.getName(), donor.getPhone(), donor.isDeleted(), new DocumentDTO(donor.getDocument()));
    }
}
