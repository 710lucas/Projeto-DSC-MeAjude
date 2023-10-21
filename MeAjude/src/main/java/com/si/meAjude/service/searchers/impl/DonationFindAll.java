package com.si.meAjude.service.searchers.impl;

import com.si.meAjude.models.Donation;
import com.si.meAjude.service.searchers.dtos.DonationSearchContent;
import com.si.meAjude.service.searchers.enums.DonationSearchCriterion;
import com.si.meAjude.service.searchers.DonationSearcher;
import com.si.meAjude.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class DonationFindAll implements DonationSearcher {

    @Autowired
    private DonationRepository donationRepository;

    @Override
    public Page<Donation> search(Pageable pageable, DonationSearchContent donationContent) {
        return donationRepository.findAll(pageable);
    }

    @Override
    public DonationSearchCriterion getCriterion() {
        return DonationSearchCriterion.ALL;
    }
}
