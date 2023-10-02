package com.si.meAjude.service.dtos.campanha;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public record CampanhaDTO(

         boolean ativa,
         String titulo,
         String descricao,
         BigDecimal meta,
         LocalDateTime dataInicio,
         LocalDateTime dataFinal,
         UsuarioDTO criador,
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
            new UsuarioDTO(campanha.getCriador()),
            DoacaoDTO.doacaoToDTO(campanha.getDoacoes()),
            campanha.getValorArrecadado()
            );
    }
}

