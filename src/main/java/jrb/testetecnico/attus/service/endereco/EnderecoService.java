package jrb.testetecnico.attus.service.endereco;

import jrb.testetecnico.attus.domain.dto.EnderecoDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;

import java.util.List;
import java.util.UUID;

public interface EnderecoService {

    EnderecoDto inserir(UUID pessoaId, EnderecoForm enderecoForm);

    List<EnderecoDto> buscarEnderecos(UUID pessoaId);

    Void definirEnderecoPrincipal(UUID pessoaId, UUID enderecoId);

    EnderecoDto atualizar(UUID enderecoId, EnderecoForm enderecoForm);

    EnderecoDto buscarEnderecosPorId(UUID enderecoId);

}
