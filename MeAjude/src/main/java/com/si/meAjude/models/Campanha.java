package com.si.meAjude.models;


import com.si.meAjude.exceptions.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "CAMPANHAS")
@Getter
public class Campanha{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean ativa;
    private String titulo;
    private String descricao;
    private BigDecimal meta;
    private LocalDateTime dataFinal;
    private LocalDateTime dataInicio = LocalDateTime.now();
    @ManyToOne
    private Usuario criador;

    @OneToMany(mappedBy = "campanha")
    private List<Doacao> doacoes = new ArrayList<>();

    private BigDecimal valorArrecadado = BigDecimal.ZERO;

    public Campanha(Usuario criador, String titulo, String descricao, BigDecimal meta, LocalDateTime dataFinal){
        this.criador = criador;
        this.titulo = titulo;
        this.descricao = descricao;
        this.meta = meta;
        this.dataFinal = dataFinal;
        this.ativa = true;
    }

    public Campanha(){

    }

    public void setId(long id) {
        this.id = id;
    }

    protected void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public void ativar(){
        setAtiva(true);
    }

    public void desAtivar(){
        setAtiva(false);
    }

    public void setTitulo(@NotNull @NotBlank @NotEmpty @Size(max = 100) String titulo) throws TituloInvalidoException {
        this.titulo = titulo;
    }

    public void setDescricao(@NotNull @NotBlank @NotEmpty @Size(max = 1000) String descricao) throws DescricaoInvalidaException {
        this.descricao = descricao;
    }

    public void setMeta(@NotNull BigDecimal meta) throws MetaInvalidaException {
        if(meta.doubleValue() <= 0)
            throw new MetaInvalidaException("O valor da meta não pode ser menor ou igual a zero");
        this.meta = meta;
    }

    public void setDataFinal(@NotNull LocalDateTime dataFinal) throws DataInvalida {
        if(dataFinal.isBefore(LocalDateTime.now()))
            throw new DataInvalida("A data final não pode ser no passado");
        this.dataFinal = dataFinal;
    }

    public void setCriador(@NotNull Usuario criador) throws CriadorInvalidoException{
        this.criador = criador;
    }

    public void adicionarDoacao(@NotNull Doacao doacao) throws DoacaoInvalidaException {
        this.doacoes.add(doacao);
        valorArrecadado = valorArrecadado.add(doacao.getValorDoado());
    }

    public void setValorArrecadado(@NotNull BigDecimal valorArrecadado) throws DoacaoInvalidaException {
        if (valorArrecadado.compareTo(this.valorArrecadado) <= 0 || valorArrecadado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DoacaoInvalidaException("O valor arrecadado não pode ser menor ou igual ao valor que já foi arrecadado, ou menor ou igual a zero");
        }
        this.valorArrecadado = valorArrecadado;
    }

}

