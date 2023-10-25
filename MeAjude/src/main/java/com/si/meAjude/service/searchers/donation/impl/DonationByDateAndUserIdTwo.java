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
public class DonationByDateAndUserIdTwo implements DonationSearcher {

    @Autowired
    private DonationRepository donationRepository;

    private Page<Donation> searchByDateAndUserId(Pageable peagle,LocalDate date, Long userId){
        return donationRepository.findAllByDateAndUserId(peagle,date, userId);
    }

    @Override
    public Page<Donation> search(Pageable pageable, DonationSearchContent donationContent) {
        return searchByDateAndUserId(pageable, donationContent.getDate(), donationContent.getUserId());
    }

    @Override
    public DonationSearchCriterion getCriterion() {
        return DonationSearchCriterion.DATE_AND_USER_ID;
    }
}
