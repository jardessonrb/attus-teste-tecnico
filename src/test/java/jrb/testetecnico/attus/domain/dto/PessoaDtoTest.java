package jrb.testetecnico.attus.domain.dto;

import jrb.testetecnico.attus.domain.model.PessoaModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class PessoaDtoTest {

    @Test
    @DisplayName("Teste para validar o parse correto dos valores do model para o DTO")
    void testeMetodoPessoaModelParaPessoaDto(){
        PessoaModel pessoaModel = PessoaModel
                .builder()
                .nomeCompleto("Maria da Silva Alguma Coisa")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .build();
        pessoaModel.setUuid(UUID.randomUUID());
        pessoaModel.setId(1L);

        PessoaDto pessoaDto = PessoaDto.toDto(pessoaModel);
        Assertions.assertNotNull(pessoaDto.getId());
        Assertions.assertNotNull(pessoaDto.getNomeCompleto());
        Assertions.assertNotNull(pessoaDto.getDataNascimento());
    }
}
