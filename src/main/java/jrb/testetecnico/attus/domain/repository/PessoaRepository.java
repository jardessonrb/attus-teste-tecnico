package jrb.testetecnico.attus.domain.repository;

import jrb.testetecnico.attus.domain.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

    Optional<PessoaModel> findByUuid(UUID pessoaUuid);
}
