package com.si.meAjude.service.dtos.donor;

import com.si.meAjude.models.Donor;
import com.si.meAjude.service.dtos.documento.DocumentDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DonorSaveDTO(

        @NotBlank
        String name,

        @NotBlank
        String phone,

        @NotNull
        DocumentDTO documentDTO

) {
    public DonorSaveDTO(Donor donor){
        this(donor.getName(), donor.getPhone(), new DocumentDTO(donor.getDocument()));
    }

    public Donor toUser(){
        Donor donor = new Donor();
        donor.setName(name);
        donor.setPhone(phone);
        donor.setDocument(documentDTO.toDocument());
        return donor;
    }

}
