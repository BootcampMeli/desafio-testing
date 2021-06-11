package com.desafiotesting.alberto.DTO;

import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.TreeMap;

public class ResponseDTO {

    private String nome;
    private String bairro;
    private Double metros;
    private Double valor;
    private ComodoDTO maior;
    private TreeMap<String, Double> mapaComodos;

    public ResponseDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Double getMetros() {
        return metros;
    }

    public void setMetros(Double metros) {
        this.metros = metros;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ComodoDTO getMaior() {
        return maior;
    }

    public void setMaior(ComodoDTO maior) {
        this.maior = maior;
    }

    public TreeMap<String, Double> getMapaComodos() {
        return mapaComodos;
    }

    public void setMapaComodos(TreeMap<String, Double> mapaComodos) {
        this.mapaComodos = mapaComodos;
    }
}
