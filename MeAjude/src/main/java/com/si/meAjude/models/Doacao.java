package com.si.meAjude.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

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
    private Date dataDoacao;

    @NotNull
    private BigDecimal valorDoado;

    public BigDecimal getValorDoado(){
        return valorDoado;
    }

}
