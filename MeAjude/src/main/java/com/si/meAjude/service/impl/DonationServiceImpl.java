package com.si.meAjude.service.impl;

import com.si.meAjude.models.Donation;
import com.si.meAjude.repositories.DonorRepository;
import com.si.meAjude.service.searchers.factorys.DonationSearcherFactory;
import com.si.meAjude.service.searchers.dtos.DonationSearchContent;
import com.si.meAjude.service.searchers.DonationSearcher;
import com.si.meAjude.repositories.CampanhaRepository;
import com.si.meAjude.repositories.DonationRepository;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.DonationService;
import com.si.meAjude.service.dtos.doacao.DonationDTO;
import com.si.meAjude.service.dtos.doacao.DonationSaveDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CampanhaRepository campaignRepository;

    @Autowired
    DonationSearcherFactory donationSearcherFactory;

    @Autowired
    private DonorRepository donorRepository;

    @Override
    public DonationDTO save(DonationSaveDTO donationDTO) {
        Donation donation = donationDTO.toDonation(campaignRepository, donorRepository);
        return new DonationDTO(donationRepository.save(donation));
    }

    @Override
    public DonationDTO getById(Long id) {
        return new DonationDTO(donationRepository.getById(id));
    }

    @Override
    public Page<DonationDTO> getAll(Pageable page,  DonationSearchContent searchContent) {
        DonationSearcher donationSearcher = donationSearcherFactory.getSearcher(searchContent.criterion());
        return donationSearcher.search(page, searchContent).map(DonationDTO::new);
    }
}
