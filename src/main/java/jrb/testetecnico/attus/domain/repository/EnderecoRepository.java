package jrb.testetecnico.attus.domain.repository;

import jrb.testetecnico.attus.domain.model.Endereco;
import jrb.testetecnico.attus.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByUuid(UUID enderecoId);

    List<Endereco> findByPessoa(Pessoa pessoaModel);

}
