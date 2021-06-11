package com.desafiotesting.alberto.DTO;

import java.util.TreeMap;

public class ValoresBairros {
/*
    // Lista com diferentes bairros e seus valores por metro
    CENTRO(1500.00),
    PINHEIRINHO(800.00),
    BPS(650.00),
    MEDICINA(400.00),
    VARGINHA(500.00),
    AVENIDA(300.00);
*//*
    private Double proçoPorMetro;

    ValoresBairros(Double proçoPorMetro) {
        this.proçoPorMetro = proçoPorMetro;
    }

    public Double getProçoPorMetro() {
        return proçoPorMetro;
    }
*/
    private TreeMap<String, Double> bairroValor = new TreeMap<>();

    public Double getBairroValor(String bairro) throws NullPointerException {
        if (bairroValor.containsKey(bairro)){
            return bairroValor.get(bairro);
        } else{
            throw new NullPointerException();
        }


    }

    public void setBairroValor() {
        bairroValor.put("Centro", 1500.00);
        bairroValor.put("Pinheirinho", 800.00);
        bairroValor.put("BPS", 650.00);
        bairroValor.put("Medicina", 400.00);
        bairroValor.put("Varginha", 500.00);
        bairroValor.put("Avenida", 300.00);
    }
}
