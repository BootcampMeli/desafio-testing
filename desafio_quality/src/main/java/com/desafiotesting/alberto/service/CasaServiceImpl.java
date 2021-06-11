package com.desafiotesting.alberto.service;

import com.desafiotesting.alberto.DTO.CasaDTO;
import com.desafiotesting.alberto.DTO.ComodoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

@Service
public class CasaServiceImpl implements CasaService {

    // US01 Calcula total de metros quadrados
    public Double calcularMetragemCasa(List<ComodoDTO> comodoDTOList){
        Double metragem = 0.0;
        for(ComodoDTO comodoDTO : comodoDTOList){
            metragem += comodoDTO.getComprimentoComodo() * comodoDTO.getLarguraComodo();
        }
        return metragem;
    }

    // US02 Indica o valor da casa com base nos quartos e medidas
    public Double calcularValorCasa(CasaDTO casaDTO){
        Double valorCasa = 0.0, valor;
        valorCasa = calcularMetragemCasa(casaDTO.getComodoDTOList()) * casaDTO.getValorBairro();
        return valorCasa;
    }

    // US03 Determina o maior comodo
    public ComodoDTO determinarMaiorComodo(List<ComodoDTO> comodoDTOList){
        ComodoDTO maiorComodo = new ComodoDTO();
        for (ComodoDTO comodoDTO : comodoDTOList){
            comodoDTO.setAreaComodo(comodoDTO.getLarguraComodo(), comodoDTO.getComprimentoComodo());
            if (comodoDTO.getAreaComodo() > maiorComodo.getAreaComodo()){
                maiorComodo = comodoDTO;
            }
        }
        return maiorComodo;
    }

    // US04 Determina o numero de metros quadrados de cada comodo
    public TreeMap<String, Double> determinarMetragemPorQuarto(List<ComodoDTO> comodoDTOList){
        TreeMap<String, Double> valorComodoMap = new TreeMap<>();
        for (ComodoDTO comodoDTO : comodoDTOList){
            comodoDTO.setAreaComodo(comodoDTO.getLarguraComodo(), comodoDTO.getComprimentoComodo());
            valorComodoMap.put(comodoDTO.getNomeComodo(), comodoDTO.getAreaComodo());
        }
        return valorComodoMap;
    }


}
