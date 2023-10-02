package com.si.meAjude.service.dtos.doacao;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.service.dtos.campanha.CampanhaDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DoacaoDTO(Long UserId, CampanhaDTO campanhaDTO, LocalDate data, BigDecimal valorDoado) {
    public DoacaoDTO(Doacao doacao){
        this(doacao.getId(), new CampanhaDTO(doacao.getCampanha()), doacao.getData(), doacao.getValorDoado());
    }

    public static List<DoacaoDTO> doacaoToDTO(List<Doacao> doacaoList){
        return doacaoList.stream().map(DoacaoDTO::new).collect(Collectors.toList());
    }
}
