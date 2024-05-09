package jrb.testetecnico.attus.service.endereco.impl;

import jrb.testetecnico.attus.domain.dto.EnderecoDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;
import jrb.testetecnico.attus.domain.model.Endereco;
import jrb.testetecnico.attus.domain.model.Pessoa;
import jrb.testetecnico.attus.domain.repository.EnderecoRepository;
import jrb.testetecnico.attus.domain.repository.PessoaRepository;
import jrb.testetecnico.attus.service.endereco.EnderecoService;
import jrb.testetecnico.attus.shared.exception.EntityNotFoundExcepion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<EnderecoDto> buscarEnderecosPorPessoa(UUID pessoaId) {
        Pessoa pessoaModel = buscarPessoaPorId(pessoaId);

        List<Endereco> enderecosBuscados = enderecoRepository.findByPessoa(pessoaModel);

        return enderecosBuscados.stream().map(EnderecoDto::toDto).toList();
    }

    @Override
    public EnderecoDto atualizar(UUID enderecoId, EnderecoForm enderecoForm) {
        Endereco enderecoModel = buscarEnderecoPorId(enderecoId);

        enderecoModel.setCep(enderecoForm.cep());
        enderecoModel.setCidade(enderecoForm.cidade());
        enderecoModel.setEstado(enderecoForm.estado());
        enderecoModel.setNumero(enderecoForm.numero());
        enderecoModel.setLogradouro(enderecoForm.logradouro());
        enderecoModel.setIsEnderecoPrincipal(enderecoModel.getIsEnderecoPrincipal());

        enderecoModel = enderecoRepository.save(enderecoModel);

        return EnderecoDto.toDto(enderecoModel);
    }

    @Override
    public EnderecoDto buscarEnderecosPorId(UUID enderecoId) {
        Endereco enderecoModel = buscarEnderecoPorId(enderecoId);

        return EnderecoDto.toDto(enderecoModel);
    }

    private Pessoa buscarPessoaPorId(UUID pessoaId){
        return pessoaRepository
                .findByUuid(pessoaId)
                .orElseThrow(() -> new EntityNotFoundExcepion("Nenhuma pessoa encontrada para o id "+pessoaId));
    }

    private Endereco buscarEnderecoPorId(UUID enderecoId){
        return enderecoRepository
                .findByUuid(enderecoId)
                .orElseThrow(() -> new EntityNotFoundExcepion("Nenhum endereço encontrado para o id "+enderecoId));

    }
}
