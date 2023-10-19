package com.si.meAjude.service.dtos.doacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.si.meAjude.models.Donation;
import com.si.meAjude.repositories.CampaignRepository;
import com.si.meAjude.repositories.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DonationDTO(
        Long userId,
        Long campaignId,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date,
        BigDecimal value) {
    public DonationDTO(Donation donation){
        this(donation.getUser().getId(), donation.getCampaign().getId(), donation.getDate(), donation.getDonationValue());
    }
}
