package com.si.meAjude.service.dtos.campanha;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioSaveDTO;
import lombok.Getter;
import lombok.Setter;

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
         UsuarioSaveDTO criador,
         List<DoacaoDTO> doacoes,
         BigDecimal valorArrecadado
){

    public CampanhaDTO(Campanha campanha){
        this(
            campanha.isAtiva(),
            campanha.getTitulo(),
            campanha.getDescricao(),
            campanha.getMeta(),
            campanha.getDataInicio(),
            campanha.getDataFinal(),
            new UsuarioSaveDTO(campanha.getCriador()),
            DoacaoDTO.doacaoToDTO(campanha.getDoacoes()),
            campanha.getValorArrecadado()
            );
    }
}

