package jrb.testetecnico.attus.service.endereco.impl;

import jrb.testetecnico.attus.domain.dto.EnderecoDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;
import jrb.testetecnico.attus.domain.model.EnderecoModel;
import jrb.testetecnico.attus.domain.model.PessoaModel;
import jrb.testetecnico.attus.domain.repository.EnderecoRepository;
import jrb.testetecnico.attus.domain.repository.PessoaRepository;
import jrb.testetecnico.attus.service.endereco.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<EnderecoDto> buscarEnderecosPorPessoa(UUID pessoaId) {
        PessoaModel pessoaModel = buscarPessoaPorId(pessoaId);

        List<EnderecoModel> enderecosBuscados = enderecoRepository.findByPessoa(pessoaModel);

        return enderecosBuscados.stream().map(EnderecoDto::toDto).toList();
    }

    @Override
    public EnderecoDto atualizar(UUID enderecoId, EnderecoForm enderecoForm) {
        EnderecoModel enderecoModel = buscarEnderecoPorId(enderecoId);

        enderecoModel.setCep(enderecoForm.cep());
        enderecoModel.setCidade(enderecoForm.cidade());
        enderecoModel.setEstado(enderecoForm.estado());
        enderecoModel.setNumero(enderecoForm.numero());
        enderecoModel.setLogradouro(enderecoForm.logradouro());

        enderecoModel = enderecoRepository.save(enderecoModel);

        return EnderecoDto.toDto(enderecoModel);
    }

    @Override
    public EnderecoDto buscarEnderecosPorId(UUID enderecoId) {
        EnderecoModel enderecoModel = buscarEnderecoPorId(enderecoId);

        return EnderecoDto.toDto(enderecoModel);
    }

    private PessoaModel buscarPessoaPorId(UUID pessoaId){
        return pessoaRepository
                .findByUuid(pessoaId)
                .orElseThrow(() -> new NoSuchElementException("Nenhuma pessoa encontrada para o id "+pessoaId));
    }

    private EnderecoModel buscarEnderecoPorId(UUID enderecoId){
        return enderecoRepository
                .findByUuid(enderecoId)
                .orElseThrow(() -> new NoSuchElementException("Nenhum endereço encontrado para o id "+enderecoId));

    }
}
