package com.si.meAjude.service;



import com.si.meAjude.service.searchers.dtos.DonationSearchContent;
import com.si.meAjude.service.dtos.doacao.DonationDTO;
import com.si.meAjude.service.dtos.doacao.DonationSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DonationService {
     DonationDTO save (DonationSaveDTO doacao);
     DonationDTO getById(Long id);
     Page<DonationDTO> getAll(Pageable page, DonationSearchContent donationDTO);
}
