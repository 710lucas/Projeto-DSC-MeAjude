package com.si.meAjude.models.searchers.campaign;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CampaignSearchContent(
        CampaignSearchCriterion criterion,
        Long userId,
        LocalDate finalDate,
        boolean active,
        BigDecimal goal

) {}
