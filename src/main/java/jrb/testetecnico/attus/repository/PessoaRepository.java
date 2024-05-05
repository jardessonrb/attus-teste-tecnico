package jrb.testetecnico.attus.repository;

import jrb.testetecnico.attus.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
}
