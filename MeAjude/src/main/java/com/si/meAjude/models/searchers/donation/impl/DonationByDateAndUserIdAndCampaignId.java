package com.si.meAjude.models.searchers.donation.impl;

import com.si.meAjude.models.Donation;
import com.si.meAjude.models.searchers.donation.DonationSearchContent;
import com.si.meAjude.models.searchers.donation.DonationSearchCriterion;
import com.si.meAjude.models.searchers.donation.DonationSearcher;
import com.si.meAjude.repositories.DonationRepository;
import com.si.meAjude.service.dtos.doacao.DonationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DonationByDateAndUserIdAndCampaignId implements DonationSearcher {

    @Autowired
    DonationRepository donationRepository;

    private Page<Donation> searchByDateAndUserIdAndCampaignId(Pageable peagle, LocalDate date, Long userId, Long campaignId){
        return donationRepository.findAllByDateAndUserIdAndCampaignId(peagle,date, userId, campaignId);
    }

    @Override
    public Page<Donation> search(Pageable pageable, DonationSearchContent donationContent) {
        return searchByDateAndUserIdAndCampaignId(pageable,donationContent.date(), donationContent.userId(), donationContent.campaignId());
    }

    @Override
    public DonationSearchCriterion getCriterion() {
        return DonationSearchCriterion.DATE_AND_USER_ID_AND_CAMPAIGN_ID;
    }
}
