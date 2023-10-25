package com.si.meAjude.service.dtos.campaign;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.Donation;
import com.si.meAjude.service.dtos.donation.DonationDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record CampaignGetDTO(

        boolean active,
        String title,
        String description,
        BigDecimal goal,
        LocalDate startingDate,
        LocalDate finalDate,
        boolean deleted,
        Long creatorId,
        @JsonManagedReference
        List<DonationDTO> donations,
        BigDecimal raisedMoney,
        Long id


)

{
    public CampaignGetDTO(Campaign campaign){
        this(campaign.isActive(),campaign.getTitle(), campaign.getDescription(), campaign.getGoal(),
                campaign.getStartingDate(), campaign.getFinalDate(), campaign.isDeleted(),
                campaign.getCreator().getId(), toDTO(campaign.getDonations()), campaign.getRaisedMoney(), campaign.getId());
    }
    private static List<DonationDTO> toDTO(List<Donation> donations){
        List<DonationDTO> doacoesDTO = new ArrayList<>();
        for(Donation d : donations)
            doacoesDTO.add(new DonationDTO(d));
        return doacoesDTO;
    }
}
