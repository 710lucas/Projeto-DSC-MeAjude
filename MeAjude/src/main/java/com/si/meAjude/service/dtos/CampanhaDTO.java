package com.si.meAjude.service.dtos;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CampanhaDTO {

    private boolean ativa;
    private String titulo;
    private String descricao;
    private BigDecimal meta;
    private LocalDateTime dataFinal;
    private LocalDateTime dataInicio;
    private Usuario criador;
    private List<Doacao> doacoes;
    private BigDecimal valorArrecadado = BigDecimal.ZERO;

    public CampanhaDTO(Campanha c){
        this.ativa = c.isAtiva();
        this.titulo = c.getTitulo();
        this.descricao = c.getDescricao();
        this.meta = c.getMeta();
        this.dataFinal = c.getDataFinal();
        this.criador = c.getCriador();
        this.doacoes = c.getDoacoes();
        this.valorArrecadado = c.getValorArrecadado();
        this.dataInicio = c.getDataInicio();
    }

    public CampanhaDTO(){

    }

    public CampanhaDTO(boolean ativa, String titulo, String descricao, BigDecimal meta, LocalDateTime dataFinal, Usuario criador, List<Doacao> doacoes, BigDecimal valorArrecadado) {
        this.ativa = ativa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.meta = meta;
        this.dataFinal = dataFinal;
        this.criador = criador;
        this.doacoes = doacoes;
        this.valorArrecadado = valorArrecadado;
    }
}
