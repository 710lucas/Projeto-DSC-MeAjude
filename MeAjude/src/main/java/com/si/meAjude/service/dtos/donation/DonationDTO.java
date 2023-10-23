package com.si.meAjude.service.dtos.donation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.si.meAjude.models.Donation;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DonationDTO(
        Long donationId,
        Long userId,
        Long campaignId,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date,
        BigDecimal value) {
    public DonationDTO(Donation donation){
        this(donation.getId(), donation.getUser().getId(), donation.getCampaign().getId(), donation.getDate(), donation.getDonationValue());
    }
}
