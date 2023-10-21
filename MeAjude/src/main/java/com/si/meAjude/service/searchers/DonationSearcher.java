package com.si.meAjude.service.searchers;

import com.si.meAjude.models.Donation;
import com.si.meAjude.service.searchers.dtos.DonationSearchContent;
import com.si.meAjude.service.searchers.enums.DonationSearchCriterion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonationSearcher {
    Page<Donation> search(Pageable pageable, DonationSearchContent donationContent);

    DonationSearchCriterion getCriterion();
}
