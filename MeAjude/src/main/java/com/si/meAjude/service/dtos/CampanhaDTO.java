package com.si.meAjude.service.dtos;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CampanhaDTO {

    private boolean ativa;
    private String titulo;
    private String descricao;
    private double meta;
    private LocalDateTime dataFinal;
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
    }

    public CampanhaDTO(){

    }

    public CampanhaDTO(boolean ativa, String titulo, String descricao, double meta, LocalDateTime dataFinal, Usuario criador, List<Doacao> doacoes, BigDecimal valorArrecadado) {
        this.ativa = ativa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.meta = meta;
        this.dataFinal = dataFinal;
        this.criador = criador;
        this.doacoes = doacoes;
        this.valorArrecadado = valorArrecadado;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    public BigDecimal getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(BigDecimal valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }
}
