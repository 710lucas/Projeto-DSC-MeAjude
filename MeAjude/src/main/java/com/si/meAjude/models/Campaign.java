package com.si.meAjude.models;


import com.si.meAjude.exceptions.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "CAMPANHAS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private boolean ativa;
    @NotNull
    @NotEmpty
    private String titulo;
    private String descricao;
    @Positive
    private BigDecimal meta;
    @Future
    private LocalDateTime dataFinal;
//    @FutureOrPresent
    private LocalDateTime dataInicio = LocalDateTime.now();
    @ManyToOne
    @NotNull
    private User criador;

    @OneToMany(mappedBy = "campaign")
    private List<Donation> doacoes = new ArrayList<>();

    private BigDecimal valorArrecadado = BigDecimal.ZERO;
    @NotNull
    private boolean deletado;


    public void adicionarDoacao(Donation donation) throws DoacaoInvalidaException {
        if(donation == null)
            throw new DoacaoInvalidaException("A doaçao informada é inválida");
        doacoes.add(donation);
    }

    public Donation getDoacao(Long id) throws DoacaoInvalidaException {
        for(Donation d : doacoes)
            if(d.getId() == id) return d;
        throw new DoacaoInvalidaException("O id " + id + " é inválido");
    }
}

