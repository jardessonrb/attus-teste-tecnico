package jrb.testetecnico.attus.service;

import jrb.testetecnico.attus.domain.dto.PessoaDto;
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

}
