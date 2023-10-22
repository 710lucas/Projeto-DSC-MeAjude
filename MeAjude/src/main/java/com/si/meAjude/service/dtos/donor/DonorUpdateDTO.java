package com.si.meAjude.service.dtos.donor;

import com.si.meAjude.models.Donor;
import jakarta.validation.constraints.NotNull;


public record DonorUpdateDTO(
        @NotNull Long id,
        String name,
        String phone )
  {

    public DonorUpdateDTO(Donor donor) {
        this(donor.getId(), donor.getName(), donor.getPhone());
    }
}