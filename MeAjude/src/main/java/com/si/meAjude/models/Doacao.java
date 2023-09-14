package com.si.meAjude.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Doacao {

    @Id
    private long id;

    private double valorDoado;

    public double getValorDoado(){
        return valorDoado;
    }

}
