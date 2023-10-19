package com.si.meAjude.service.impl;

import com.si.meAjude.models.Donation;
import com.si.meAjude.models.searchers.donation.DonationSearcherFactory;
import com.si.meAjude.models.searchers.donation.DonationSearchContent;
import com.si.meAjude.models.searchers.donation.DonationSearcher;
import com.si.meAjude.repositories.CampaignRepository;
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
    private CampaignRepository campaignRepository;

    @Autowired
    DonationSearcherFactory donationSearcherFactory;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DonationDTO save(DonationSaveDTO doacaoDTO) {
        Donation donation = doacaoDTO.toDonation(campaignRepository, userRepository);
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
