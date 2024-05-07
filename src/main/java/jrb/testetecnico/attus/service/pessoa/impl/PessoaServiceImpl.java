package jrb.testetecnico.attus.service.pessoa.impl;

import jrb.testetecnico.attus.domain.dto.PessoaDto;
import jrb.testetecnico.attus.domain.form.EnderecoForm;
import jrb.testetecnico.attus.domain.form.PessoaForm;
import jrb.testetecnico.attus.domain.model.EnderecoModel;
import jrb.testetecnico.attus.domain.model.PessoaModel;
import jrb.testetecnico.attus.domain.repository.EnderecoRepository;
import jrb.testetecnico.attus.domain.repository.PessoaRepository;
import jrb.testetecnico.attus.service.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public PessoaDto criar(PessoaForm pessoaForm) {
        PessoaModel pessoa = PessoaModel
                .builder()
                .nomeCompleto(pessoaForm.nomeCompleto())
                .dataNascimento(pessoaForm.dataNascimento())
                .build();

        pessoa = pessoaRepository.save(pessoa);

        return PessoaDto.toDto(pessoa);
    }

    @Override
    public Page<PessoaDto> buscar(String nome, LocalDate dataNascimento, Pageable paginacao) {
        nome = Objects.isNull(nome) ? "" : nome;
        Page<PessoaModel> pessoasPage = pessoaRepository.findPessoaByNomeCompletoAndDataNascimento(nome, dataNascimento, paginacao);

        return pessoasPage.map(PessoaDto::toDto);
    }

    @Override
    public PessoaDto buscarPorId(UUID pessoaId) {
        PessoaModel pessoa = pessoaRepository
                .findByUuid(pessoaId)
                .orElseThrow(
                        () -> new NoSuchElementException("Nenhuma pessoa encontrada para o id "+pessoaId)
                );

        return PessoaDto.toDto(pessoa);
    }

    @Override
    public PessoaDto editar(UUID pessoaId, PessoaForm pessoaForm) {
        PessoaModel pessoa = pessoaRepository
                .findByUuid(pessoaId)
                .orElseThrow(
                        () -> new NoSuchElementException("Nenhuma pessoa encontrada para o id "+pessoaId)
                );

        pessoa.setNomeCompleto(pessoaForm.nomeCompleto());
        pessoa.setDataNascimento(pessoaForm.dataNascimento());

        pessoa = pessoaRepository.save(pessoa);

        return PessoaDto.toDto(pessoa);
    }

    @Override
    public void definirEnderecoPrincipal(UUID pessoaId, UUID enderecoId) {
        PessoaModel pessoaModel = buscarPessoaPorId(pessoaId);
        EnderecoModel enderecoModel = buscarEnderecoPorId(enderecoId);

        pessoaModel.setEnderecoPrincipal(enderecoModel);
        pessoaRepository.save(pessoaModel);
        return;
    }

    @Override
    public PessoaDto inserirEndereco(UUID pessoaId, EnderecoForm enderecoForm) {
        PessoaModel pessoaModel = buscarPessoaPorId(pessoaId);
        EnderecoModel enderecoModel = EnderecoModel
                .builder()
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
