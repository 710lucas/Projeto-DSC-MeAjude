package com.si.meAjude.service.dtos.donation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.si.meAjude.models.Donation;
import com.si.meAjude.repositories.CampanhaRepository;
import com.si.meAjude.repositories.UserRepository;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DonationSaveDTO(
        @NotNull
        Long userId,
        @NotNull
        Long campaignId,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        LocalDate date,
        @NotNull
        @Min(value = 1)
        BigDecimal value) {

        public Donation toDonation(CampanhaRepository campaignRepository, UserRepository donorRepository){
                Donation donation = new Donation();
                donation.setCampaign(campaignRepository.getById(campaignId));
                donation.setUser(donorRepository.getById(userId));
                donation.setDate(date);
                donation.setDonationValue(value);
                return donation;
        }
}
