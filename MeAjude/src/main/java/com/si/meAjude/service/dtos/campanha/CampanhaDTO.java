package com.si.meAjude.service.dtos.campanha;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioSaveDTO;

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

    @Override
    public boolean ativa() {
        return ativa;
    }


    public String getTitulo() {
        return titulo;
    }

    @Override
    public String descricao() {
        return descricao;
    }

    @Override
    public BigDecimal meta() {
        return meta;
    }


    public LocalDateTime getDataInicio() {
        return dataInicio;
    }


    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    @Override
    public UsuarioSaveDTO criador() {
        return criador;
    }

    @Override
    public List<DoacaoDTO> doacoes() {
        return doacoes;
    }

    @Override
    public BigDecimal valorArrecadado() {
        return valorArrecadado;
    }
}

