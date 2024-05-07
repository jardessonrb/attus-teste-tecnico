package jrb.testetecnico.attus.service.endereco;

import jrb.testetecnico.attus.domain.dto.EnderecoDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;

import java.util.List;
import java.util.UUID;

public interface EnderecoService {

    List<EnderecoDto> buscarEnderecosPorPessoa(UUID pessoaId);

    EnderecoDto atualizar(UUID enderecoId, EnderecoForm enderecoForm);

    EnderecoDto buscarEnderecosPorId(UUID enderecoId);

}
