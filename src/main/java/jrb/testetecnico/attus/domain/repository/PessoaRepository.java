package jrb.testetecnico.attus.domain.repository;

import jrb.testetecnico.attus.domain.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
}
