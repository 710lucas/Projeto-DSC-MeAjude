package com.si.meAjude.models.searchers.donation;

import com.fasterxml.jackson.annotation.JsonFormat;

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
