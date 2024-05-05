package jrb.testetecnico.attus.service.pessoa;

import jrb.testetecnico.attus.domain.dto.PessoaDto;
import jrb.testetecnico.attus.domain.form.PessoaForm;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PessoaService {

    PessoaDto criar(PessoaForm pessoaForm);

    Page<PessoaDto> buscar(String nome);

    PessoaDto buscarPorId(UUID pessoaId);

    PessoaDto editar(UUID pessoaId, PessoaForm pessoaForm);
}
