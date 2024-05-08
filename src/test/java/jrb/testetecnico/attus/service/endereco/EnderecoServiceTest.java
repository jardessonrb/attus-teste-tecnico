package jrb.testetecnico.attus.service.endereco;

import jrb.testetecnico.attus.domain.dto.EnderecoDto;
import jrb.testetecnico.attus.domain.dto.PessoaDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;
import jrb.testetecnico.attus.domain.form.PessoaForm;
import jrb.testetecnico.attus.service.pessoa.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class EnderecoServiceTest {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaService pessoaService;

    @Test
    @DisplayName("Teste para buscar os endereços de uma pessoa")
    void testeBuscarEnderecoPorPessoa(){
        PessoaForm pessoaForm = getPessoaForm();
        PessoaDto pessoaDto = Assertions.assertDoesNotThrow(() -> pessoaService.criar(pessoaForm));
        UUID pessoaId = pessoaDto.getId();
        int quantidadeDeEnderecosEsperados = 2;
        List<EnderecoDto> enderecos = Assertions.assertDoesNotThrow(() -> enderecoService.buscarEnderecosPorPessoa(pessoaId));

        Assertions.assertEquals(quantidadeDeEnderecosEsperados, enderecos.size());

    }

    @Test
    @DisplayName("Teste para atualização de endereço")
    void testeAtualizacaoEndereco(){
        PessoaForm pessoaForm = getPessoaForm();
        PessoaDto pessoaDto = Assertions.assertDoesNotThrow(() -> pessoaService.criar(pessoaForm));
        UUID enderecoId = pessoaDto.getEnderecoPrincipal().getId();
        EnderecoDto enderecoDto = pessoaDto.getEnderecoPrincipal();

        String novoLogradouro = "Rua da Flores";
        String novaCidade     = "Teresina";
        Integer novoNumero    = 25689;

        EnderecoForm enderecoForm = new EnderecoForm(
                novoLogradouro,
                enderecoDto.getCep(),
                novaCidade,
                enderecoDto.getEstado(),
                novoNumero);

        enderecoDto = Assertions.assertDoesNotThrow(() -> enderecoService.atualizar(enderecoId, enderecoForm));
        Assertions.assertEquals(novoLogradouro, enderecoDto.getLogradouro());
        Assertions.assertEquals(novaCidade, enderecoDto.getCidade());
        Assertions.assertEquals(novoNumero, enderecoDto.getNumero());

    }

    @Test
    @DisplayName("Teste para buscar endereço por id")
    void testeBuscarEnderecoPorId(){
        PessoaForm pessoaForm = getPessoaForm();
        PessoaDto pessoaDto = Assertions.assertDoesNotThrow(() -> pessoaService.criar(pessoaForm));
        UUID enderecoId = pessoaDto.getEnderecoPrincipal().getId();

        EnderecoDto endereco = Assertions.assertDoesNotThrow(() -> enderecoService.buscarEnderecosPorId(enderecoId));
        Assertions.assertEquals(enderecoId, endereco.getId());
    }

    private PessoaForm getPessoaForm(){
        return  PessoaForm
                .builder()
                .nomeCompleto("Maria Fulano de Sousa")
                .dataNascimento(LocalDate.of(1998, 10, 1))
                .enderecoPrincipal(new EnderecoForm("Rua 30", "5894255", "Teresina", "Piauí", 1000))
                .enderecos(Arrays.asList(
                        new EnderecoForm("Rua 30", "60260111", "Teresina", "Piauí", 1100)
                ))
                .build();
    }
}
