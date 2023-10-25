package com.si.meAjude.models.searchers.donation;

import com.si.meAjude.models.Donation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonationSearcher {
    Page<Donation> search(Pageable pageable, DonationSearchContent donationContent);

    DonationSearchCriterion getCriterion();
}
