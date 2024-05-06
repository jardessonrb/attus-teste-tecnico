package jrb.testetecnico.attus.domain.repository;

import jrb.testetecnico.attus.domain.model.PessoaModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    @DisplayName("Teste de repository")
    void testeParaMetodoDeBuscarPessoaPorUuid(){
        PessoaModel pessoaModel = PessoaModel
                .builder()
                .nomeCompleto("Beltrano Silva")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .build();

        pessoaModel = pessoaRepository.save(pessoaModel);
        UUID pessoaId = pessoaModel.getUuid();
        UUID pessoaIdFake = UUID.randomUUID();
        Optional<PessoaModel> optionalPessoaModel = Assertions.assertDoesNotThrow(() -> pessoaRepository.findByUuid(pessoaId));
        Optional<PessoaModel> optionalPessoaModelEmpty = Assertions.assertDoesNotThrow(() -> pessoaRepository.findByUuid(pessoaIdFake));

        Assertions.assertTrue(optionalPessoaModel.isPresent());
        Assertions.assertFalse(optionalPessoaModelEmpty.isPresent());
    }
}
