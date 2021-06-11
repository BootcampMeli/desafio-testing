package com.desafiotesting.alberto.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

public class CasaDTO {

    // Classe para os atributos da casa
    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^([A-Z]+)([a-z]|[A-Z]|[0-9]|\\s)+", message = "Nome da propriedade deve ser iniciado por maiuscula")
    @Length(max = 30, message = "O comprimento não pode exceder 30 caracteres.")
    private String propName;

    @NotBlank(message = "O bairro não pode estar vazio")
    @Length(max = 45, message = "O comprimento não pode exceder 45 caracteres.")
    private String propDistrict;

    @Valid
    @NotEmpty/*(message = "")*/
    private List<ComodoDTO> comodoDTOList;

    @JsonIgnore
    private Integer quantidadeComodos;

    @JsonIgnore
    private Double valorBairro;

    public CasaDTO(String propName, String propDistrict, List<ComodoDTO> comodoDTOList) {
        ValoresBairros valoresBairros = new ValoresBairros();
        valoresBairros.setBairroValor();
        this.valorBairro = valoresBairros.getBairroValor(propDistrict);
        this.propName = propName;
        this.propDistrict = propDistrict;
        this.quantidadeComodos = comodoDTOList.size();
        this.comodoDTOList = comodoDTOList;
    }



    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropDistrict() {
        return propDistrict;
    }

    public void setPropDistrict(String propDistrict) {
        this.propDistrict = propDistrict;
    }

    public List<ComodoDTO> getComodoDTOList() {
        return comodoDTOList;
    }

    public void setComodoDTOList(List<ComodoDTO> comodoDTOList) {
        this.comodoDTOList = comodoDTOList;
    }

    public Integer getQuantidadeComodos() {
        return quantidadeComodos;
    }

    public void setQuantidadeComodos(Integer quantidadeComodos) {
        this.quantidadeComodos = quantidadeComodos;
    }

    public Double getValorBairro() {
        return valorBairro;
    }

    public void setValorBairro(Double valorBairro) {
        this.valorBairro = valorBairro;
    }
}
