package com.si.meAjude.service.dtos;

import com.si.meAjude.models.Campanha;
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

    private List<CampanhaDTO> campanhas = new ArrayList<>();

    public void adicionarCampanha(CampanhaDTO campanha){
        this.campanhas.add(campanha);
    }

    public ListaCampanhasDTO(List<Campanha> campanhas, CriterioEnum criterio){
        List<CampanhaDTO> newCampanhas = new ArrayList<>();
        for(Campanha c : campanhas) {
            switch (criterio){
                case ATIVAS_DATA, ATIVAS_TITULO -> {
                    if(c.isAtiva())
                        newCampanhas.add(new CampanhaDTO(c));
                }
                case ENCERRADAS_DATA -> {
                    if(!c.isAtiva())
                        newCampanhas.add(new CampanhaDTO(c));
                }
                case META_ATINGIDA -> {
                    if(c.getValorArrecadado().compareTo(c.getMeta()) >= 0)
                        newCampanhas.add(new CampanhaDTO(c));
                }
                default -> newCampanhas.add(new CampanhaDTO(c));
            }
        }

        this.campanhas = newCampanhas;
        switch (criterio){
            case ATIVAS_DATA, ENCERRADAS_DATA, META_ATINGIDA -> Collections.sort(this.campanhas, new DataComparator());
            case ATIVAS_TITULO -> Collections.sort(this.campanhas, new TituloComparator());
        }

    }

}
