package com.si.meAjude.service.searchers.donation;

import com.si.meAjude.models.Donation;
import com.si.meAjude.service.searchers.donation.dtos.DonationSearchContent;
import com.si.meAjude.service.searchers.donation.enums.DonationSearchCriterion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonationSearcher {
    Page<Donation> search(Pageable pageable, DonationSearchContent donationContent);

    DonationSearchCriterion getCriterion();
}
