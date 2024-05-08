package jrb.testetecnico.attus.service.municipioestado;

import jrb.testetecnico.attus.domain.dto.EstadoDto;
import jrb.testetecnico.attus.domain.dto.MunicipioDto;
import jrb.testetecnico.attus.service.estadomunicipio.MunicipioEstadoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class MunicipioEstadoServiceTest {

    @Autowired
    private MunicipioEstadoService municipioEstadoService;

    @Test
    @DisplayName("Teste para validar a busca correta de municípios por nome sem filtro de estados")
    void testeBuscarMunicipiosPorNomeSemFiltroDeEstado(){
        String nomeMunicipio = "Alagoinha";
        Long quantidadeDeMunicipiosEsperados = 4L;
        Pageable paginacao = PageRequest.of(0, 50);

        Page<MunicipioDto> municipios = Assertions.assertDoesNotThrow(() -> municipioEstadoService.buscarMunicipios(nomeMunicipio, null, paginacao));
        Assertions.assertEquals(quantidadeDeMunicipiosEsperados, municipios.getTotalElements());
    }

    @Test
    @DisplayName("Teste para validar a busca correta de municípios por nome com filtro de estados")
    void testeBuscarMunicipiosPorNomeComFiltroDeEstado(){
        String nomeMunicipio = "Alagoinha";
        String ufEstado      = "PE";
        Long quantidadeDeMunicipiosEsperados = 1L;
        Pageable paginacao = PageRequest.of(0, 50);

        Page<MunicipioDto> municipios = Assertions.assertDoesNotThrow(() -> municipioEstadoService.buscarMunicipios(nomeMunicipio, ufEstado, paginacao));
        Assertions.assertEquals(quantidadeDeMunicipiosEsperados, municipios.getTotalElements());
        Assertions.assertEquals(ufEstado, municipios.stream().toList().get(0).getUfEstado());
        Assertions.assertEquals(nomeMunicipio, municipios.stream().toList().get(0).getNomeMunicipio());
    }

    @Test
    @DisplayName("Teste para buscar municípios com filtro de estado com diferentes caixas")
    void testeBuscarMunicipiosPorNomeComFiltroDeEstadoEmDiferentesCaixas(){
        String nomeMunicipio = "Alagoinha";
        String ufEstado      = "Pe";
        Long quantidadeDeMunicipiosEsperados = 1L;
        Pageable paginacao = PageRequest.of(0, 50);

        Page<MunicipioDto> municipios = Assertions.assertDoesNotThrow(() -> municipioEstadoService.buscarMunicipios(nomeMunicipio, ufEstado, paginacao));
        Assertions.assertEquals(quantidadeDeMunicipiosEsperados, municipios.getTotalElements());
        Assertions.assertEquals(nomeMunicipio, municipios.stream().toList().get(0).getNomeMunicipio());
    }

    @Test
    @DisplayName("Teste para buscar municípios com filtro de estado com UF inválido")
    void testeBuscarMunicipiosPorNomeComFiltroDeUFDeEstadoInvalido(){
        String nomeMunicipio = "Alagoinha";
        String ufEstado      = "Pepe";
        Long quantidadeDeMunicipiosEsperados = 0L;
        Pageable paginacao = PageRequest.of(0, 50);

        Page<MunicipioDto> municipios = Assertions.assertDoesNotThrow(() -> municipioEstadoService.buscarMunicipios(nomeMunicipio, ufEstado, paginacao));
        Assertions.assertEquals(quantidadeDeMunicipiosEsperados, municipios.getTotalElements());
    }

    @Test
    @DisplayName("Teste para buscar estados por filtro válido")
    void testeBuscarEstadosPorFiltroValido(){
            String nomeEstado = "Mato";
        Pageable paginacao = PageRequest.of(0, 27);
        Long quantidadeDeEstadosEsperados = 2L;

        Page<EstadoDto> estados = Assertions.assertDoesNotThrow(() -> municipioEstadoService.buscarEstados(nomeEstado, paginacao));
        Assertions.assertEquals(quantidadeDeEstadosEsperados, estados.getTotalElements());

    }
    @Test
    @DisplayName("Teste para buscar estados por filtro como UF válido")
    void testeBuscarEstadosPorFiltroComoUFValido(){
        String nomeEstado = "MT";
        Pageable paginacao = PageRequest.of(0, 27);
        Long quantidadeDeEstadosEsperados = 1L;

        Page<EstadoDto> estados = Assertions.assertDoesNotThrow(() -> municipioEstadoService.buscarEstados(nomeEstado, paginacao));
        Assertions.assertEquals(quantidadeDeEstadosEsperados, estados.getTotalElements());

    }
}
