package jrb.testetecnico.attus.service.pessoa.impl;

import jrb.testetecnico.attus.dto.PessoaDto;
import jrb.testetecnico.attus.form.PessoaForm;
import jrb.testetecnico.attus.service.pessoa.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Override
    public PessoaDto criar(PessoaForm pessoaForm) {
        return null;
    }

    @Override
    public Page<PessoaDto> buscar(String nome) {
        return null;
    }

    @Override
    public PessoaDto buscarPorId(UUID pessoaId) {
        return null;
    }

    @Override
    public PessoaDto editar(UUID pessoaId, PessoaForm pessoaForm) {
        return null;
    }
}
