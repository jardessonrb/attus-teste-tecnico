package jrb.testetecnico.attus.service.pessoa;

import jrb.testetecnico.attus.domain.dto.PessoaDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;
import jrb.testetecnico.attus.domain.form.PessoaForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface PessoaService {

    PessoaDto criar(PessoaForm pessoaForm);

    Page<PessoaDto> buscar(String nome, LocalDate dataNascimento, Pageable paginacao);

    PessoaDto buscarPorId(UUID pessoaId);

    PessoaDto editar(UUID pessoaId, PessoaForm pessoaForm);

    void definirEnderecoPrincipal(UUID pessoaId, UUID enderecoId);

    PessoaDto inserirEndereco(UUID pessoaId, EnderecoForm enderecoForm);
}
