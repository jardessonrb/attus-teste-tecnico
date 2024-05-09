package jrb.testetecnico.attus.domain.repository;

import jrb.testetecnico.attus.domain.model.PessoaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

    Optional<PessoaModel> findByUuid(UUID pessoaUuid);

    @Query(value = "SELECT p FROM PessoaModel p " +
            "WHERE (LENGTH(:nome) = 0 and :dataNascimento is null) " +
            "OR (p.nomeCompleto ILIKE CONCAT('%', :nome, '%') and p.dataNascimento = :dataNascimento)" +
            "OR (LENGTH(:nome) = 0 and :dataNascimento is not null and p.dataNascimento = :dataNascimento)" +
            "OR (LENGTH(:nome) > 0 and :dataNascimento is null and  p.nomeCompleto ILIKE CONCAT('%', :nome, '%'))",
            nativeQuery = false)
    Page<PessoaModel> findPessoaByNomeCompletoAndDataNascimento(@Param("nome") String nome, @Param("dataNascimento")LocalDate dataNascimento, Pageable paginacao);
}
