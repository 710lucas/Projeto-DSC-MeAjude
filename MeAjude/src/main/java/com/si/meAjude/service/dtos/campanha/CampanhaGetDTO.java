package com.si.meAjude.service.dtos.campanha;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.si.meAjude.models.Campanha;
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
    public CampanhaGetDTO(Campanha campanha){
        this(campanha.isAtiva(),campanha.getTitulo(), campanha.getDescricao(), campanha.getMeta(),
                campanha.getDataInicio(), campanha.getDataFinal(), campanha.isDeletado(),
                campanha.getCriador().getId(), toDTO(campanha.getDoacoes()), campanha.getValorArrecadado(), campanha.getId());
    }
    private static List<DonationDTO> toDTO(List<Donation> doacoes){
        List<DonationDTO> doacoesDTO = new ArrayList<>();
        for(Donation d : doacoes)
            doacoesDTO.add(new DonationDTO(d));
        return doacoesDTO;
    }
}
