package com.si.meAjude.models;


import com.si.meAjude.exceptions.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Campanha {

    @Id
    private long id;

    private String estado; //Ativa - Encerrada
    private String titulo;
    private String descricao;
    private double meta;
    private LocalDateTime dataFinal;
    @ManyToOne
    private Usuario criador;
    @OneToMany
    private List<Doacao> doacoes;

    public Campanha(Usuario criador, String titulo, String descricao, double meta, LocalDateTime dataFinal) throws CriadorInvalidoException, TituloInvalidoException, DescricaoInvalidaException, MetaInvalidaException, DataInvalida {
        if(criador == null)
            throw new CriadorInvalidoException("O criador informado não existe");
        if(titulo.length() > 100)
            throw new TituloInvalidoException("O titulo da campanha não pode ser mais longo que 100 caracteres");
        if(descricao.length() > 1000)
            throw new DescricaoInvalidaException("A descricao não pode conter mais que 1000 caracteres");
        if(meta <= 0)
            throw new MetaInvalidaException("O valor da meta atingida é inválido");
        if(dataFinal == null || dataFinal.isBefore(LocalDateTime.now()) || dataFinal.isEqual(LocalDateTime.now()))
            throw new DataInvalida("A data informada é inválida");
        validarString(titulo);
        validarString(descricao);

        this.criador = criador;
        this.titulo = titulo;
        this.descricao = descricao;
        this.meta = meta;
        this.dataFinal = dataFinal;
        this.estado = "ativa";

    }

    public Campanha(){

    }

    public void changeEstado(String estado) throws EstadoInvalidoException {
        estado = estado.toLowerCase().trim();
        if(!estado.equals("ativa") || !estado.equals("encerrada"))
            throw new EstadoInvalidoException("O estado precisa ser ativa ou encerrada");
        this.estado = estado;
    }

    public void changeTitulo(String titulo) throws TituloInvalidoException {
        if(titulo.length() > 100)
            throw new TituloInvalidoException("O titulo não pode conter mais de 100 caracteres");
        validarString(titulo);
        this.titulo = titulo;
    }

    public void changeMeta(double meta) throws MetaInvalidaException{
        if(meta <= 0)
            throw new MetaInvalidaException("O valor da meta deve ser maior que 0");
        this.meta = meta;
    }

    public void changeDataFinal(LocalDateTime dataFinal) throws DataInvalida {
        if(dataFinal == null || dataFinal.isBefore(LocalDateTime.now()) || dataFinal.isEqual(LocalDateTime.now()))
            throw new DataInvalida("A data informada é inválida");
        this.dataFinal = dataFinal;
    }

    public void changeDescricao(String descricao) throws TituloInvalidoException, DescricaoInvalidaException {
        if(descricao.length() > 1-00)
            throw new TituloInvalidoException("A descrição não pode conter mais de 1000 caracteres");
        validarString(descricao);
        this.descricao = descricao;
    }

    private boolean validarString(String valor) throws TituloInvalidoException {

        if(valor == null)
            throw new TituloInvalidoException("Não foi possivel utilizar o valor informado");
        else if(valor.contains("<") || valor.contains(">") || valor.contains("\\") || valor.contains("/")) //pensar em regex para fazer essa verificação
            throw new TituloInvalidoException("O valor contêm caracteres inválidos");
        else if(valor.length() == 0 || valor.equals("") || valor.replace(" ", "").equals(""))
            throw new TituloInvalidoException("O valor não pode ser apenas espaços ou vazio");

        return true;
    }



}
