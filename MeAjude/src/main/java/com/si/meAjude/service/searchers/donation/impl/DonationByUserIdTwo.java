package com.si.meAjude.service.searchers.donation.impl;

import com.si.meAjude.models.Donation;
import com.si.meAjude.repositories.DonationRepository;
import com.si.meAjude.service.searchers.donation.dtos.DonationSearchContent;
import com.si.meAjude.service.searchers.donation.enums.DonationSearchCriterion;
import com.si.meAjude.service.searchers.donation.DonationSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class DonationByUserIdTwo implements DonationSearcher {

    @Autowired
    private DonationRepository donationRepository;

    private Page<Donation> searchByUserId(Pageable peagle, Long userId){
        return donationRepository.findAllByUserId(peagle,userId);
    }

    @Override
    public Page<Donation> search(Pageable pageable, DonationSearchContent donationContent) {
        return searchByUserId(pageable, donationContent.getUserId());
    }

    @Override
    public DonationSearchCriterion getCriterion() {
        return DonationSearchCriterion.USER_ID;
    }
}
