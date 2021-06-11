package com.desafiotesting.alberto.controller;

import com.desafiotesting.alberto.DTO.CasaDTO;
import com.desafiotesting.alberto.DTO.ComodoDTO;
import com.desafiotesting.alberto.DTO.ResponseDTO;
import com.desafiotesting.alberto.service.CasaServiceImpl;
import com.sun.source.tree.Tree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.TreeMap;

@RestController
public class Controller {

    private final CasaServiceImpl casaService;

    public Controller(CasaServiceImpl casaService) {
        this.casaService = casaService;
    }

    @PostMapping("/")
    public ResponseEntity<String> home(){
        String message = "Página inicial \n Vá para uma destas páginas: \n /metragem " +
                "\n /valorCasa \n /maiorComodo \n /metragemQuartos \n /completa";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/metragem")
    public ResponseEntity<String> metragem(@Valid @RequestBody CasaDTO casaDTO){
        Double metros = casaService.calcularMetragemCasa(casaDTO.getComodoDTOList());
        return new ResponseEntity<>("Total de metros quadrados da propriedade: "
                + metros.toString(), HttpStatus.OK);
    }

    @PostMapping("/valorCasa")
    public ResponseEntity<String> valorCasa(@Valid @RequestBody CasaDTO casaDTO){
        Double valor = casaService.calcularValorCasa(casaDTO);
        return new ResponseEntity<>("Valor total da casa: "
                + valor.toString(), HttpStatus.OK);
    }

    @PostMapping("/maiorComodo")
    public ResponseEntity<ComodoDTO> maiorComodo(@Valid @RequestBody CasaDTO casaDTO){
        ComodoDTO maior = casaService.determinarMaiorComodo(casaDTO.getComodoDTOList());
        return new ResponseEntity<ComodoDTO>(maior, HttpStatus.OK);
    }

    @PostMapping("/metragemQuartos")
    public ResponseEntity<TreeMap<String, Double>> metragemQuartos(@Valid @RequestBody CasaDTO casaDTO){
        TreeMap<String, Double> comodosMap = casaService.determinarMetragemPorQuarto(casaDTO.getComodoDTOList());
        return new ResponseEntity<TreeMap<String, Double>>(comodosMap, HttpStatus.OK);
    }

    @PostMapping("/completa")
    public ResponseEntity<ResponseDTO> respostaCompleta(@Valid @RequestBody CasaDTO casaDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setNome(casaDTO.getPropName());
        responseDTO.setBairro(casaDTO.getPropDistrict());
        responseDTO.setMaior(casaService.determinarMaiorComodo(casaDTO.getComodoDTOList()));
        responseDTO.setMapaComodos(casaService.determinarMetragemPorQuarto(casaDTO.getComodoDTOList()));
        responseDTO.setMetros(casaService.calcularMetragemCasa(casaDTO.getComodoDTOList()));
        responseDTO.setValor(casaService.calcularValorCasa(casaDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
