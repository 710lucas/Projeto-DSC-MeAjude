package com.si.meAjude.service.dtos.campanha;

import com.si.meAjude.models.campaign;
import com.si.meAjude.models.comparators.DataComparator;
import com.si.meAjude.models.comparators.TituloComparator;
import com.si.meAjude.models.enums.CriterioEnum;
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

    public CampaignListDTO(List<campaign> campanhas, CriterioEnum criterio){
        List<CampaignGetDTO> newCampanhas = new ArrayList<>();
        for(campaign c : campanhas) {
            if(c.isDeleted())
                continue;
            switch (criterio){
                case ATIVAS_DATA, ATIVAS_TITULO -> {
                    if(c.isActive())
                        newCampanhas.add(new CampaignGetDTO(c));
                }
                case ENCERRADAS_DATA -> {
                    if(!c.isActive())
                        newCampanhas.add(new CampaignGetDTO(c));
                }
                case META_ATINGIDA -> {
                    if(c.getRaisedMoney().compareTo(c.getGoal()) >= 0)
                        newCampanhas.add(new CampaignGetDTO(c));
                }
                default -> newCampanhas.add(new CampaignGetDTO(c));
            }
        }

        this.campaigns = newCampanhas;
        switch (criterio){
            case ATIVAS_DATA, ENCERRADAS_DATA, META_ATINGIDA -> Collections.sort(this.campaigns, new DataComparator());
            case ATIVAS_TITULO -> Collections.sort(this.campaigns, new TituloComparator());
        }

    }

}
