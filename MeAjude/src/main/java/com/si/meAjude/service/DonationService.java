package com.si.meAjude.service;



import com.si.meAjude.service.searchers.donation.dtos.DonationSearchContent;
import com.si.meAjude.service.dtos.donation.DonationDTO;
import com.si.meAjude.service.dtos.donation.DonationSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DonationService {
     DonationDTO save (DonationSaveDTO doacao);
     DonationDTO getById(Long id);
     Page<DonationDTO> getAll(Pageable page, DonationSearchContent donationDTO);
}
