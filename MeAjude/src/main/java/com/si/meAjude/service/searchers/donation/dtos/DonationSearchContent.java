package com.si.meAjude.service.searchers.donation.dtos;

import com.si.meAjude.service.searchers.donation.enums.DonationSearchCriterion;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DonationSearchContent{

    private DonationSearchCriterion criterion;
    private Long userId;
    private Long campaignId;
    private LocalDate date;
    private BigDecimal value;

    public DonationSearchContent(Long userId, Long campaignId, LocalDate date, BigDecimal value) {
        this.userId = userId;
        this.campaignId = campaignId;
        this.date = date;
        this.value = value;
    }

    public DonationSearchContent() {
    }

    public DonationSearchCriterion getCriterion(){
        if(date != null && userId != null && campaignId != null) return DonationSearchCriterion.DATE_AND_USER_ID_AND_CAMPAIGN_ID;
        if(userId != null && campaignId != null) return DonationSearchCriterion.USER_ID_AND_CAMPAIGN_ID;
        if(date != null && userId != null) return DonationSearchCriterion.DATE_AND_USER_ID;
        if(date != null && campaignId != null) return DonationSearchCriterion.DATE_AND_CAMPAIGN_ID;
        if(userId != null) return DonationSearchCriterion.USER_ID;
        if(date != null) return DonationSearchCriterion.DATE;
        if(campaignId != null) return DonationSearchCriterion.CAMPAIGN_ID;
        return DonationSearchCriterion.ALL;
    }
}
