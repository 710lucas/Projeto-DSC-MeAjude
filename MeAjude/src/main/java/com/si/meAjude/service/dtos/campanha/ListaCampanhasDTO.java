package com.si.meAjude.service.dtos.campanha;

import com.si.meAjude.models.Campaign;
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
public class ListaCampanhasDTO {

    private List<CampanhaGetDTO> campanhas = new ArrayList<>();

    public void adicionarCampanha(CampanhaGetDTO campanha){
        this.campanhas.add(campanha);
    }

    public ListaCampanhasDTO(List<Campaign> campaigns, CriterioEnum criterio){
        List<CampanhaGetDTO> newCampanhas = new ArrayList<>();
        for(Campaign c : campaigns) {
            switch (criterio){
                case ATIVAS_DATA, ATIVAS_TITULO -> {
                    if(c.isAtiva())
                        newCampanhas.add(new CampanhaGetDTO(c));
                }
                case ENCERRADAS_DATA -> {
                    if(!c.isAtiva())
                        newCampanhas.add(new CampanhaGetDTO(c));
                }
                case META_ATINGIDA -> {
                    if(c.getValorArrecadado().compareTo(c.getMeta()) >= 0)
                        newCampanhas.add(new CampanhaGetDTO(c));
                }
                default -> newCampanhas.add(new CampanhaGetDTO(c));
            }
        }

        this.campanhas = newCampanhas;
        switch (criterio){
            case ATIVAS_DATA, ENCERRADAS_DATA, META_ATINGIDA -> Collections.sort(this.campanhas, new DataComparator());
            case ATIVAS_TITULO -> Collections.sort(this.campanhas, new TituloComparator());
        }

    }

}
