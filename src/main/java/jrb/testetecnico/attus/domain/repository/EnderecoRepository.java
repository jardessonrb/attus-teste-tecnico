package jrb.testetecnico.attus.domain.repository;

import jrb.testetecnico.attus.domain.model.EnderecoModel;
import jrb.testetecnico.attus.domain.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

    Optional<EnderecoModel> findByUuid(UUID enderecoId);

    List<EnderecoModel> findByPessoa(PessoaModel pessoaModel);

}
