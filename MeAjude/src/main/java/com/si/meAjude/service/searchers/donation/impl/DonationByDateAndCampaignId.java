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
public class DonationByDateAndCampaignId implements DonationSearcher {

    @Autowired
    private DonationRepository donationRepository;

    private Page<Donation> searchByDateAndCampaignId(Pageable peagle, LocalDate date, Long campaignId){
        return donationRepository.findAllByDateAndCampaignId(peagle,date, campaignId);
    }

    @Override
    public Page<Donation> search(Pageable pageable, DonationSearchContent donationContent) {
        return searchByDateAndCampaignId(pageable,donationContent.getDate(), donationContent.getCampaignId());
    }

    @Override
    public DonationSearchCriterion getCriterion() {
        return DonationSearchCriterion.DATE_AND_CAMPAIGN_ID;
    }
}
