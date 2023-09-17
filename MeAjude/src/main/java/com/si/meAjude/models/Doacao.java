package com.si.meAjude.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Data
@Table(name = "DOACOES")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "campanha_id")
    @NotNull
    private Campanha campanha;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDoacao;

    @NotNull
    private BigDecimal valorDoado;


    public Doacao( Usuario usuario, Campanha campanha, LocalDate dataDoacao, BigDecimal valorDoado) {
        this.usuario = usuario;
        this.campanha = campanha;
        this.dataDoacao = dataDoacao;
        this.valorDoado = valorDoado;
    }

    public Doacao() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public LocalDate getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public BigDecimal getValorDoado() {
        return valorDoado;
    }

    public void setValorDoado(BigDecimal valorDoado) {
        this.valorDoado = valorDoado;
    }
}
