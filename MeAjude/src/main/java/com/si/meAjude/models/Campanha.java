package com.si.meAjude.models;


import com.si.meAjude.exceptions.*;
import jakarta.persistence.*;
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

    public void setTitulo(String titulo) throws TituloInvalidoException {
        if(titulo == null)
            throw new TituloInvalidoException("O titulo informado é nulo");
        if(titulo.length() > 100)
            throw new TituloInvalidoException("O titulo não pode conter mais que 100 caracteres");
        if(titulo.replace(" ", "").isEmpty())
            throw new TituloInvalidoException("O titulo não pode ser vazio");
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) throws DescricaoInvalidaException {
        if(descricao == null)
            throw new DescricaoInvalidaException("A descricao informada é nula");
        if(descricao.length() > 1000)
            throw new DescricaoInvalidaException("A descricao não pode conter mais que 1000 caracteres");
        if(descricao.replace(" ", "").isEmpty())
            throw new DescricaoInvalidaException("A descrição não pode ser vazia");
        this.descricao = descricao;
    }

    public void setMeta(BigDecimal meta) throws MetaInvalidaException {
        if(meta.doubleValue() <= 0)
            throw new MetaInvalidaException("O valor da meta não pode ser menor ou igual a zero");
        this.meta = meta;
    }

    public void setDataFinal(LocalDateTime dataFinal) throws DataInvalida {
        if(dataFinal == null)
            throw new DataInvalida("A data informada é nula");
        this.dataFinal = dataFinal;
    }

    public void setCriador(Usuario criador) throws CriadorInvalidoException{
        if(criador == null)
            throw new CriadorInvalidoException("O criador é nulo");
        this.criador = criador;
    }

    public void adicionarDoacao(Doacao doacao) throws DoacaoInvalidaException {
        if(doacao == null)
            throw new DoacaoInvalidaException("A doação é inválida");
        this.doacoes.add(doacao);
        valorArrecadado = valorArrecadado.add(doacao.getValorDoado());
    }

    public void setValorArrecadado(BigDecimal valorArrecadado) throws DoacaoInvalidaException {
        if (valorArrecadado.compareTo(this.valorArrecadado) <= 0 || valorArrecadado.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DoacaoInvalidaException("O valor arrecadado não pode ser menor ou igual ao valor que já foi arrecadado, ou menor ou igual a zero");
        }
        this.valorArrecadado = valorArrecadado;
    }

}

