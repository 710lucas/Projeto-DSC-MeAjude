package com.si.meAjude.service.searcher.donation;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DonationSearchContent(
        DonationSearchCriterion criterion,
        Long userId,
        Long campaignId,
        LocalDate date,
        BigDecimal value
) {
}
