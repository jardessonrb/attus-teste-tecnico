package jrb.testetecnico.attus.service.pessoa.impl;

import jrb.testetecnico.attus.domain.dto.PessoaDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;
import jrb.testetecnico.attus.domain.form.PessoaForm;
import jrb.testetecnico.attus.domain.model.Endereco;
import jrb.testetecnico.attus.domain.model.Pessoa;
import jrb.testetecnico.attus.domain.repository.EnderecoRepository;
import jrb.testetecnico.attus.domain.repository.PessoaRepository;
import jrb.testetecnico.attus.service.pessoa.PessoaService;
import jrb.testetecnico.attus.shared.exception.EntityNotFoundExcepion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Validated
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public PessoaDto criar(PessoaForm pessoaForm) {
        List<Endereco> enderecos = Objects.nonNull(pessoaForm.enderecos()) ?
                pessoaForm.enderecos().stream().map(Endereco::toModel).collect(Collectors.toList()) :
                new ArrayList<>();

        if(Objects.nonNull(pessoaForm.enderecoPrincipal())){
            enderecos.add(Endereco.toModel(pessoaForm.enderecoPrincipal(), true));
        }

        Pessoa pessoa = Pessoa
                .builder()
                .nomeCompleto(pessoaForm.nomeCompleto())
                .dataNascimento(pessoaForm.dataNascimento())
                .enderecos(enderecos)
                .build();

        pessoa = pessoaRepository.save(pessoa);

        return PessoaDto.toDto(pessoa);
    }

    @Override
    public Page<PessoaDto> buscar(String nome, LocalDate dataNascimento, Pageable paginacao) {
        nome = Objects.isNull(nome) ? "" : nome;
        Page<Pessoa> pessoasPage = pessoaRepository.findPessoaByNomeCompletoAndDataNascimento(nome, dataNascimento, paginacao);

        return pessoasPage.map(PessoaDto::toDto);
    }

    @Override
    public PessoaDto buscarPorId(UUID pessoaId) {
        Pessoa pessoa = pessoaRepository
                .findByUuid(pessoaId)
                .orElseThrow(
                        () -> new EntityNotFoundExcepion("Nenhuma pessoa encontrada para o id "+pessoaId)
                );

        return PessoaDto.toDto(pessoa);
    }

    @Override
    public PessoaDto editar(UUID pessoaId, PessoaForm pessoaForm) {
        Pessoa pessoa = pessoaRepository
                .findByUuid(pessoaId)
                .orElseThrow(
                        () -> new EntityNotFoundExcepion("Nenhuma pessoa encontrada para o id "+pessoaId)
                );

        pessoa.setNomeCompleto(pessoaForm.nomeCompleto());
        pessoa.setDataNascimento(pessoaForm.dataNascimento());

        pessoa = pessoaRepository.save(pessoa);

        return PessoaDto.toDto(pessoa);
    }

    @Override
    public void definirEnderecoPrincipal(UUID pessoaId, UUID enderecoId) {
        Pessoa pessoaModel = buscarPessoaPorId(pessoaId);

        //Caso em que o endereço já é o principal
        if(Objects.nonNull(pessoaModel.getEnderecoPrincipal()) && pessoaModel.getEnderecoPrincipal().getId().equals(enderecoId)){
            return;
        }

        pessoaModel.getEnderecos().stream().forEach(endereco -> {
            if(endereco.getIsEnderecoPrincipal() && !endereco.getUuid().equals(enderecoId)){
                endereco.setIsEnderecoPrincipal(false);
            }

            if(endereco.getUuid().equals(enderecoId)){
                endereco.setIsEnderecoPrincipal(true);
            }
        });

        pessoaRepository.save(pessoaModel);
    }

    @Override
    public PessoaDto inserirEndereco(UUID pessoaId, EnderecoForm enderecoForm) {
        Pessoa pessoaModel = buscarPessoaPorId(pessoaId);
        Endereco enderecoModel = Endereco
                .builder()
                .isEnderecoPrincipal(false)
                .cep(enderecoForm.cep())
                .cidade(enderecoForm.cidade())
                .estado(enderecoForm.estado())
                .logradouro(enderecoForm.logradouro())
                .numero(enderecoForm.numero())
                .build();

        pessoaModel.getEnderecos().add(enderecoModel);
        pessoaModel = pessoaRepository.save(pessoaModel);

        return PessoaDto.toDto(pessoaModel);
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
