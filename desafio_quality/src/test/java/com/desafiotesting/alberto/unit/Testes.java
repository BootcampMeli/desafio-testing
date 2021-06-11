package com.desafiotesting.alberto.unit;

import com.desafiotesting.alberto.DTO.CasaDTO;
import com.desafiotesting.alberto.DTO.ComodoDTO;
import com.desafiotesting.alberto.DTO.ValoresBairros;
import com.desafiotesting.alberto.service.CasaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@SpringBootTest
public class Testes {

    CasaServiceImpl casaService = new CasaServiceImpl();
    static CasaDTO casaDTO;
    static ValoresBairros valoresBairros;

    @BeforeAll
    public static void setup(){
        valoresBairros = new ValoresBairros();
        valoresBairros.setBairroValor();
        List<ComodoDTO> comodoDTOList = new ArrayList<>();
        comodoDTOList.add(new ComodoDTO("Comodo1", 12.0, 10.0));
        comodoDTOList.add(new ComodoDTO("Comodo2", 11.0, 15.0));
        comodoDTOList.add(new ComodoDTO("Comodo4", 25.0, 33.0));
        comodoDTOList.add(new ComodoDTO("Comodo3", 1.0, 1.0));
        casaDTO = new CasaDTO("Casa1", "BPS", comodoDTOList);

    }

    @Test
    void metragemTestAssertEquals(){
        Double metragem = casaService.calcularMetragemCasa(casaDTO.getComodoDTOList());
        Double expected = 12.0 * 10.0 + 11.0 * 15.0 + 25.0 * 33.0 + 1.0 * 1.0;

        Assertions.assertEquals(expected, metragem);
    }

    @Test
    void metragemTestAssertNotEquals(){
        Double metragem = casaService.calcularMetragemCasa(casaDTO.getComodoDTOList());
        Double notExpected = 12.0 + 10.0 + 11.0 + 15.0 + 25.0 + 33.0 + 1.0 + 1.0;

        Assertions.assertNotEquals(notExpected, metragem);
    }

    @Test
    void valorCasaTestEquals(){
        Double valor = casaService.calcularValorCasa(casaDTO);
        Double expected = valoresBairros.getBairroValor("BPS")
                * (12.0 * 10.0 + 11.0 * 15.0 + 25.0 * 33.0 + 1.0 * 1.0);

        Assertions.assertEquals(expected, valor);
    }

    @Test
    void valorCasaTestNotEquals(){
        Double valor = casaService.calcularValorCasa(casaDTO);
        Double notExpected = valoresBairros.getBairroValor("Centro")
                * (12.0 * 10.0 + 11.0 * 15.0 + 25.0 * 33.0 + 1.0 * 1.0);

        Assertions.assertNotEquals(notExpected, valor);
    }

    @Test
    void maiorComodoTestEquals(){
        ComodoDTO expected = new ComodoDTO("max", 25.0, 33.0);
        expected.setAreaComodo(expected.getLarguraComodo(), expected.getComprimentoComodo());
        ComodoDTO maior = casaService.determinarMaiorComodo(casaDTO.getComodoDTOList());

        Assertions.assertEquals(expected.getAreaComodo(), maior.getAreaComodo());
    }

    @Test
    void maiorComodoTestNotEquals(){
        ComodoDTO notExpected = new ComodoDTO("max", 25.0, 33.0);
        notExpected.setAreaComodo(notExpected.getLarguraComodo(), notExpected.getComprimentoComodo());
        ComodoDTO maior = casaService.determinarMaiorComodo(casaDTO.getComodoDTOList());

        Assertions.assertNotEquals(notExpected, maior);
    }

    @Test
    void metragemPorQuartoEquals(){
        TreeMap<String, Double> expectedMap = new TreeMap<>();
        expectedMap.put("Comodo1", 12.0 * 10.0);
        expectedMap.put("Comodo2", 11.0 * 15.0);
        expectedMap.put("Comodo4", 25.0 * 33.0);
        expectedMap.put("Comodo3", 1.0 * 1.0);

        TreeMap<String, Double> actualMap = casaService.determinarMetragemPorQuarto(casaDTO.getComodoDTOList());

        Assertions.assertEquals(expectedMap, actualMap);
    }

    @Test
    void metragemPorQuartoNotEquals(){
        TreeMap<String, Double> unExpectedMap = new TreeMap<>();
        unExpectedMap.put("Comodo1", 12.0 + 10.0);
        unExpectedMap.put("Comodo2", 11.0 + 15.0);
        unExpectedMap.put("Comodo4", 25.0 + 33.0);
        unExpectedMap.put("Comodo3", 1.0 + 1.0);

        TreeMap<String, Double> actualMap = casaService.determinarMetragemPorQuarto(casaDTO.getComodoDTOList());

        Assertions.assertNotEquals(unExpectedMap, actualMap);
    }

    @Test
    void bairroExists(){
        valoresBairros.setBairroValor();
        Double actual = valoresBairros.getBairroValor(casaDTO.getPropDistrict());
        Double expected = 650.00;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void bairroNotExists(){
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
                () -> valoresBairros.getBairroValor("Bairro"));
    }
}
