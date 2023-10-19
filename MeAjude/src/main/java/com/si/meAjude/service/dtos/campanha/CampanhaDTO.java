package com.si.meAjude.service.dtos.campanha;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.Donation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record CampanhaDTO(

         boolean ativa,
         String titulo,
         String descricao,
         BigDecimal meta,
         LocalDateTime dataInicio,
         LocalDateTime dataFinal,
         boolean deletado,
         Long criadorId,
         @JsonManagedReference
         List<Donation> doacoes,
         BigDecimal valorArrecadado
){

    public CampanhaDTO(Campaign campaign){
        this(campaign.isAtiva(), campaign.getTitulo(), campaign.getDescricao(), campaign.getMeta(),
                campaign.getDataInicio(), campaign.getDataFinal(), campaign.isDeletado(),
                campaign.getCriador().getId(), campaign.getDoacoes(), campaign.getValorArrecadado());
    }



//    private static List<Doacao> toModel(List<DoacaoDTO> dtos){
//        List<Doacao> doacoes = new ArrayList<>();
//        for(DoacaoDTO d : dtos)
//            doacoes.add(new Doacao(d.));
//    }


}

