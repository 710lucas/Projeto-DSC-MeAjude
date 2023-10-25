package com.si.meAjude.service.impl;

import com.si.meAjude.models.Donation;
import com.si.meAjude.repositories.CampaignRepository;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.searchers.donation.factorys.DonationSearcherFactoryTwo;
import com.si.meAjude.service.searchers.donation.dtos.DonationSearchContent;
import com.si.meAjude.service.searchers.donation.DonationSearcher;
import com.si.meAjude.repositories.DonationRepository;
import com.si.meAjude.service.DonationService;
import com.si.meAjude.service.dtos.donation.DonationDTO;
import com.si.meAjude.service.dtos.donation.DonationSaveDTO;

import jakarta.persistence.EntityNotFoundException;
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
    DonationSearcherFactoryTwo donationSearcherFactoryTwo;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DonationDTO save(DonationSaveDTO donationDTO) {
        Donation donation = donationDTO.toDonation(campaignRepository, userRepository);
        if(donation.getCampaign().isDeleted()) throw new EntityNotFoundException("Unable to find Campaign with id "+ donation.getCampaign().getId());
        if(donation.getUser().isDeleted()) throw new EntityNotFoundException("Unable to find User with id "+ donation.getUser().getId());
        if(donation.getCampaign().getFinalDate().isBefore(donation.getDate())) throw new IllegalArgumentException("The campaign is already over");
        if(donation.getCampaign().getRaisedMoney().doubleValue() >= donation.getCampaign().getGoal().doubleValue()) throw new IllegalArgumentException("The campaign has already reached its goal");
        if(!donation.getCampaign().isActive()) throw new IllegalArgumentException("The campaign is deactivated");
        return new DonationDTO(donationRepository.save(donation));
    }

    @Override
    public DonationDTO getById(Long id) {
        return new DonationDTO(donationRepository.getById(id));
    }

    @Override
    public Page<DonationDTO> getAll(Pageable page,  DonationSearchContent searchContent) {
        DonationSearcher donationSearcher = donationSearcherFactoryTwo.getSearcher(searchContent.getCriterion());
        return donationSearcher.search(page, searchContent).map(DonationDTO::new);
    }
}
