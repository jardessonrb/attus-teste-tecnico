package jrb.testetecnico.attus.service;

import jrb.testetecnico.attus.domain.dto.PessoaDto;
import jrb.testetecnico.attus.domain.form.PessoaForm;
import jrb.testetecnico.attus.domain.model.PessoaModel;
import jrb.testetecnico.attus.service.pessoa.PessoaService;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @Test
    @DisplayName("Persistência de pessoa válida")
    void testeCriacaoPessoaValida(){
        String nomeCompleto = "Járdesson Ribeiro";
        LocalDate dataNascimento =  LocalDate.of(1999, 11, 17);
        PessoaForm pessoaForm = new PessoaForm(nomeCompleto, dataNascimento);

        PessoaDto pessoaDto = Assertions.assertDoesNotThrow(() -> pessoaService.criar(pessoaForm));
        Assertions.assertEquals(nomeCompleto, pessoaDto.getNomeCompleto());
        Assertions.assertEquals(dataNascimento, pessoaDto.getDataNascimento());
        Assertions.assertNotNull(pessoaDto.getId());
    }

    @Test
    @DisplayName("Buscar pessoa por id")
    void testeBuscarPessoaPorId(){
        String nomeCompleto = "Járdesson Ribeiro";
        LocalDate dataNascimento =  LocalDate.of(1999, 11, 17);
        PessoaForm pessoaForm = new PessoaForm(nomeCompleto, dataNascimento);

        PessoaDto pessoaDto = Assertions.assertDoesNotThrow(() -> pessoaService.criar(pessoaForm));
        UUID pessoaId = pessoaDto.getId();
        UUID pessoaIdFake = UUID.randomUUID();
        String mensagemErroEsperada = "Nenhuma pessoa encontrada para o id "+pessoaIdFake;

        Assertions.assertDoesNotThrow(() -> pessoaService.buscarPorId(pessoaId));

        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> pessoaService.buscarPorId(pessoaIdFake));
        Assertions.assertEquals(mensagemErroEsperada, exception.getMessage());
    }

    @Test
    @DisplayName("Teste para busca de pessoas por nome e data de nascimento")
    void testeBuscaPessoasPorNomeEPorDataNascimento(){
        List<PessoaForm> pessoasFake = getPessoasFake();
        for (PessoaForm pessoaForm : pessoasFake) {
            Assertions.assertDoesNotThrow(() -> pessoaService.criar(pessoaForm));
        }

        String nomeBuscado = "maria";
        Pageable paginacao = PageRequest.of(1, 10);
        Long quantidadeDeResgistrosEsperadosBuscandoPeloNome = 2L;
        Long quantidadeDeResgistrosEsperadosBuscandoPeloNomeEDataNascimento = 1L;
        Long quantidadeDeResgistrosEsperadosBuscandoPelaDataNascimento = 4L;

        LocalDate dataNascimento = LocalDate.of(1998, 10, 1);
        LocalDate dataNascimentoParaBuscaApenasComDataNascimento = LocalDate.of(2000, 1, 1);
        Page<PessoaDto> pessoasBuscadasPeloNome = Assertions.assertDoesNotThrow(() -> pessoaService.buscar(nomeBuscado, null, paginacao));

        Assertions.assertEquals(quantidadeDeResgistrosEsperadosBuscandoPeloNome, pessoasBuscadasPeloNome.getTotalElements());

        Page<PessoaDto> pessoasBuscadasPeloNomeEDataNascimento = Assertions.assertDoesNotThrow(() -> pessoaService.buscar(nomeBuscado, dataNascimento, paginacao));

        Assertions.assertEquals(quantidadeDeResgistrosEsperadosBuscandoPeloNomeEDataNascimento, pessoasBuscadasPeloNomeEDataNascimento.getTotalElements());

        Page<PessoaDto> pessoasBuscadasPorDataNascimento = Assertions.assertDoesNotThrow(() -> pessoaService.buscar(null, dataNascimentoParaBuscaApenasComDataNascimento, paginacao));

        Assertions.assertEquals(quantidadeDeResgistrosEsperadosBuscandoPelaDataNascimento, pessoasBuscadasPorDataNascimento.getTotalElements());
    }

    private List<PessoaForm> getPessoasFake(){
        return Arrays.asList(
                PessoaForm
                        .builder()
                        .nomeCompleto("Maria da Cruz Silva")
                        .dataNascimento(LocalDate.of(2000, 1, 1))
                        .build(),
                PessoaForm
                        .builder()
                        .nomeCompleto("Maria Fulano de Sousa")
                        .dataNascimento(LocalDate.of(1998, 10, 1))
                        .build(),
                PessoaForm
                        .builder()
                        .nomeCompleto("José Gomes Paulo")
                        .dataNascimento(LocalDate.of(2000, 1, 1))
                        .build(),
                PessoaForm
                        .builder()
                        .nomeCompleto("Ciclano de Alvez Beltrano")
                        .dataNascimento(LocalDate.of(2000, 1, 1))
                        .build(),
                PessoaForm
                        .builder()
                        .nomeCompleto("Attus da Silva")
                        .dataNascimento(LocalDate.of(2000, 1, 1))
                        .build()
        );
    }
}
