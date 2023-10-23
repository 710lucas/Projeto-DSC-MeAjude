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

@Component
public class DonationByCampaignId implements DonationSearcher {

    @Autowired
    private DonationRepository donationRepository;

    private Page<Donation> searchByCampaignId(Pageable peagle, Long campaignId){
        return donationRepository.findAllByCampaignId(peagle,campaignId);
    }

    @Override
    public Page<Donation> search(Pageable pageable, DonationSearchContent donationContent) {
        return searchByCampaignId(pageable, donationContent.campaignId());
    }

    @Override
    public DonationSearchCriterion getCriterion() {
        return DonationSearchCriterion.CAMPAIGN_ID;
    }
}