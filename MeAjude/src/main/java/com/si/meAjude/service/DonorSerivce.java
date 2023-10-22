package com.si.meAjude.service;

import com.si.meAjude.service.dtos.donor.DonorDTO;
import com.si.meAjude.service.dtos.donor.DonorSaveDTO;
import com.si.meAjude.service.dtos.donor.DonorUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonorSerivce {


     DonorDTO save(DonorSaveDTO dto);

     DonorDTO getById(Long id);

     Page<DonorDTO> getAll(Pageable pageable);

     DonorDTO update(DonorUpdateDTO updateDto);

     DonorDTO logicDelete(Long id);
}
