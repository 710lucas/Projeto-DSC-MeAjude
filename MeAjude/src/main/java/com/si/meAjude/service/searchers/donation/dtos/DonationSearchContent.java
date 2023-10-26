package com.si.meAjude.service.searchers.donation.dtos;

import com.si.meAjude.service.searchers.donation.enums.DonationSearchCriterion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationSearchContent{

    private DonationSearchCriterion criterion;
    private Long userId;
    private Long campaignId;
    private LocalDate date;
    private BigDecimal value;
}
