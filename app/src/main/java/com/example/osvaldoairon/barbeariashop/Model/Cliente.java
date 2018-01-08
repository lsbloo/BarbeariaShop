package com.example.osvaldoairon.barbeariashop.Model;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by osvaldoairon on 07/01/2018.
 */

public class Cliente implements Serializable{

    private String nome;
    private Double valor_cabelo;
    private Double valor_barba;
    private String id;
    private Context context;


    public Double getValor_barba() {
        return valor_barba;
    }

    public Double getValor_cabelo() {
        return valor_cabelo;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor_barba(Double valor_barba) {
        this.valor_barba = valor_barba;
    }

    public void setValor_cabelo(Double valor_cabelo) {
        this.valor_cabelo = valor_cabelo;
    }
    public Cliente(){

    }
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.context = null;
    }
}
