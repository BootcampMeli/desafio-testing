package com.desafiotesting.alberto.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class ComodoDTO {

    // Classe para os atrbutos dos comodos

    @NotNull(message = "O nome do comodo não pode estar vazio.")
    @Pattern(regexp = "^([A-Z]+)([a-z]|[A-Z]|[0-9]|\\s)+", message = "Nome do comodo deve ser iniciado por maiuscula")
    @Length(max = 30, message = "O comprimento do comodo não pode exceder 30 caracteres")
    private String nomeComodo;

    @NotNull(message = "A largura do comodo não pode estar vazia.")
    @Positive
    @Max(value = 25, message = "A largura máxima permitida por comodo é de 25 metros")
    private double larguraComodo;

    @NotNull(message = "O comprimento do comodo não pode estar vazio.")
    @Positive
    @Max(value = 33, message = "O comprimento máximo permitida por comodo é de 33 metros")
    private double comprimentoComodo;

    @JsonIgnore
    private double areaComodo;

    public ComodoDTO() {
    }

    public ComodoDTO(String nomeComodo, double larguraComodo, double comprimentoComodo) {
        this.nomeComodo = nomeComodo;
        this.larguraComodo = larguraComodo;
        this.comprimentoComodo = comprimentoComodo;
    }

    public String getNomeComodo() {
        return nomeComodo;
    }

    public void setNomeComodo(String nomeComodo) {
        this.nomeComodo = nomeComodo;
    }

    public double getLarguraComodo() {
        return larguraComodo;
    }

    public void setLarguraComodo(double larguraComodo) {
        this.larguraComodo = larguraComodo;
    }

    public double getComprimentoComodo() {
        return comprimentoComodo;
    }

    public void setComprimentoComodo(double comprimentoComodo) {
        this.comprimentoComodo = comprimentoComodo;
    }

    public double getAreaComodo() {
        return areaComodo;
    }

    public void setAreaComodo(double largura, double comprimento) {
        this.areaComodo = largura * comprimento;
    }
}
