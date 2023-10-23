package com.si.meAjude.service.dtos.campanha;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.si.meAjude.models.campaign;
import com.si.meAjude.models.Donation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record CampaignDTO(

         boolean active,
         String title,
         String description,
         BigDecimal goal,
         LocalDateTime startingDate,
         LocalDateTime finalDate,
         boolean deleted,
         Long creatorId,
         @JsonManagedReference
         List<Donation> donations,
         BigDecimal raisedMoney
){

    public CampaignDTO(campaign campaign){
        this(campaign.isActive(),campaign.getTitle(), campaign.getDescription(), campaign.getGoal(),
                campaign.getStartingDate(), campaign.getFinalDate(), campaign.isDeleted(),
                campaign.getCreator().getId(), campaign.getDonations(), campaign.getRaisedMoney());
    }



//    private static List<Doacao> toModel(List<DoacaoDTO> dtos){
//        List<Doacao> donations = new ArrayList<>();
//        for(DoacaoDTO d : dtos)
//            donations.add(new Doacao(d.));
//    }


}

