package com.si.meAjude.service.dtos.campanha;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.si.meAjude.models.campaign;
import com.si.meAjude.models.Donation;
import com.si.meAjude.service.dtos.doacao.DonationDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record CampaignGetDTO(

        boolean active,
        String title,
        String description,
        BigDecimal goal,
        LocalDateTime startingDate,
        LocalDateTime finalDate,
        boolean deleted,
        Long creatorId,
        @JsonManagedReference
        List<DonationDTO> donations,
        BigDecimal raisedMoney,
        Long id


)

{
    public CampaignGetDTO(campaign campaign){
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
