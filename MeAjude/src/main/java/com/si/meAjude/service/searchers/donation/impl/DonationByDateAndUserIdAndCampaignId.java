package com.si.meAjude.service.searchers.donation.impl;

import com.si.meAjude.models.Donation;
import com.si.meAjude.service.searchers.donation.dtos.DonationSearchContent;
import com.si.meAjude.service.searchers.donation.enums.DonationSearchCriterion;
import com.si.meAjude.service.searchers.donation.DonationSearcher;
import com.si.meAjude.repositories.DonationRepository;
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
        return searchByDateAndUserIdAndCampaignId(pageable,donationContent.getDate(), donationContent.getUserId(), donationContent.getCampaignId());
    }

    @Override
    public DonationSearchCriterion getCriterion() {
        return DonationSearchCriterion.DATE_AND_USER_ID_AND_CAMPAIGN_ID;
    }
}
