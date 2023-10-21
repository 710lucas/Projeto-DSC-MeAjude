package com.si.meAjude.service.searchers.dtos;

import com.si.meAjude.service.searchers.enums.DonationSearchCriterion;

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
