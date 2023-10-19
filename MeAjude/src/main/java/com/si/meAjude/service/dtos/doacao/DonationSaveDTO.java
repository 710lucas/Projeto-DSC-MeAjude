package com.si.meAjude.service.dtos.doacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.Donation;
import com.si.meAjude.models.User;
import com.si.meAjude.repositories.CampaignRepository;
import com.si.meAjude.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

        public Donation toDonation(CampaignRepository campaignRepository, UserRepository userRepository){
                Donation donation = new Donation();
                donation.setCampaign(getDonation(campaignRepository, campaignId));
                donation.setUser(getUser(userRepository, userId));
                donation.setDate(date);
                donation.setDonationValue(value);
                return donation;
        }

        private Campaign getDonation(CampaignRepository campaignRepository, Long campaignId){
                return campaignRepository.findById(campaignId).orElseThrow(()-> new EntityNotFoundException("Campaing not found by id: "+ campaignId));
        }

        private User getUser(UserRepository userRepository, Long userId){
                return userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found by id: "+ userId));
        }
}
