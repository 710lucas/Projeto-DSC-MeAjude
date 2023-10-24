package com.si.meAjude.service.dtos.campaign;

import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.comparators.DataComparator;
import com.si.meAjude.models.comparators.TituloComparator;
import com.si.meAjude.models.searchers.campaign.CampaignSearchCriterion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignListDTO {

    private List<CampaignGetDTO> campaigns = new ArrayList<>();

    public void adicionarCampanha(CampaignGetDTO campanha){
        this.campaigns.add(campanha);
    }

    public CampaignListDTO(List<Campaign> campanhas, CampaignSearchCriterion criterio){
        List<CampaignGetDTO> newCampanhas = new ArrayList<>();
        for(Campaign c : campanhas) {
            if(c.isDeleted())
                continue;
            switch (criterio){
                case ACTIVE_DATE, ACTIVE_TITLE -> {
                    if(c.isActive())
                        newCampanhas.add(new CampaignGetDTO(c));
                }
                case CLOSED_DATE -> {
                    if(!c.isActive())
                        newCampanhas.add(new CampaignGetDTO(c));
                }
                case GOAL_REACHED -> {
                    if(c.getRaisedMoney().compareTo(c.getGoal()) >= 0)
                        newCampanhas.add(new CampaignGetDTO(c));
                }
                default -> newCampanhas.add(new CampaignGetDTO(c));
            }
        }

        this.campaigns = newCampanhas;
        switch (criterio){
            case ACTIVE_DATE, CLOSED_DATE, GOAL_REACHED -> Collections.sort(this.campaigns, new DataComparator());
            case ACTIVE_TITLE -> Collections.sort(this.campaigns, new TituloComparator());
        }

    }

}
