package com.si.meAjude.service.dtos.campaign;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.Donation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public record CampaignDTO(

         boolean active,
         String title,
         String description,
         BigDecimal goal,
         LocalDate startingDate,
         LocalDate finalDate,
         boolean deleted,
         Long creatorId,
         @JsonManagedReference
         List<Donation> donations,
         BigDecimal raisedMoney,
         Long id
){

    public CampaignDTO(Campaign campaign){
        this(campaign.isActive(),campaign.getTitle(), campaign.getDescription(), campaign.getGoal(),
                campaign.getStartingDate(), campaign.getFinalDate(), campaign.isDeleted(),
                campaign.getCreator().getId(), campaign.getDonations(), campaign.getRaisedMoney(), campaign.getId());
    }



//    private static List<Doacao> toModel(List<DoacaoDTO> dtos){
//        List<Doacao> donations = new ArrayList<>();
//        for(DoacaoDTO d : dtos)
//            donations.add(new Doacao(d.));
//    }


}

