package com.si.meAjude.service.dtos.campaign;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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

         @JsonFormat(pattern = "dd/MM/yyyy")
         LocalDate startingDate,

         @JsonFormat(pattern = "dd/MM/yyyy")
         LocalDate finalDate,

         Long creatorId,

         BigDecimal raisedMoney,

         Long id
){

    public CampaignDTO(Campaign campaign){
        this(campaign.isActive(),campaign.getTitle(), campaign.getDescription(), campaign.getGoal(),
                campaign.getStartingDate(), campaign.getFinalDate(),
                campaign.getCreator().getId(), campaign.getRaisedMoney(), campaign.getId());
    }
}

