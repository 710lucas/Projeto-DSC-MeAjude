package com.si.meAjude.models;


import com.si.meAjude.exceptions.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean ativa;
    private String titulo;
    private String descricao;
    private double meta;
    private LocalDateTime dataFinal;
    @ManyToOne
    private Usuario criador;
    @OneToMany
    private List<Doacao> doacoes;
    private BigDecimal valorArrecadado = BigDecimal.ZERO;

    public Campanha(Usuario criador, String titulo, String descricao, double meta, LocalDateTime dataFinal) throws CriadorInvalidoException, TituloInvalidoException, DescricaoInvalidaException, MetaInvalidaException, DataInvalida {
        this.criador = criador;
        this.titulo = titulo;
        this.descricao = descricao;
        this.meta = meta;
        this.dataFinal = dataFinal;
        this.ativa = true;
    }

    public Campanha(){

    }

    public long getId() {
        return id;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMeta() {
        return meta;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public Usuario getCriador() {
        return criador;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public BigDecimal getValorArrecadado() {
        return valorArrecadado;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public void setTitulo(String titulo) throws TituloInvalidoException {
        if(titulo == null)
            throw new TituloInvalidoException("O titulo informado é nulo");
        if(titulo.length() > 100)
            throw new TituloInvalidoException("O titulo não pode conter mais que 100 caracteres");
        if(titulo.equals("") || titulo.replace(" ", "").equals("") || titulo.length() == 0)
            throw new TituloInvalidoException("O titulo não pode ser vazio");
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) throws DescricaoInvalidaException {
        if(descricao == null)
            throw new DescricaoInvalidaException("A descricao informada é nula");
        if(descricao.length() > 1000)
            throw new DescricaoInvalidaException("A descricao não pode conter mais que 1000 caracteres");
        if(descricao.equals("") || descricao.replace(" ", "").equals("") || descricao.length() == 0)
            throw new DescricaoInvalidaException("A descrição não pode ser vazia");
        this.descricao = descricao;
    }

    public void setMeta(double meta) throws MetaInvalidaException {
        if(meta <= 0)
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
