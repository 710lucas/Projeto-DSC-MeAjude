package com.si.meAjude.service.dtos.campanha;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.Donation;
import com.si.meAjude.service.dtos.doacao.DonationDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record CampanhaGetDTO  (

        boolean ativa,
        String titulo,
        String descricao,
        BigDecimal meta,
        LocalDateTime dataInicio,
        LocalDateTime dataFinal,
        boolean deletado,
        Long criadorId,
        @JsonManagedReference
        List<DonationDTO> doacoes,
        BigDecimal valorArrecadado,
        Long id


)

{
    public CampanhaGetDTO(Campaign campaign){
        this(campaign.isAtiva(), campaign.getTitulo(), campaign.getDescricao(), campaign.getMeta(),
                campaign.getDataInicio(), campaign.getDataFinal(), campaign.isDeletado(),
                campaign.getCriador().getId(), toDTO(campaign.getDoacoes()), campaign.getValorArrecadado(), campaign.getId());
    }
    private static List<DonationDTO> toDTO(List<Donation> doacoes){
        List<DonationDTO> doacoesDTO = new ArrayList<>();
        for(Donation d : doacoes)
            doacoesDTO.add(new DonationDTO(d));
        return doacoesDTO;
    }
}
