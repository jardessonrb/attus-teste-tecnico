package jrb.testetecnico.attus.domain.repository;

import jrb.testetecnico.attus.domain.model.MunicipioEstado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioEstado, Long> {

    @Query("SELECT me FROM MunicipioEstado me WHERE ((:filtro is null or  me.nomeMunicipio ILIKE CONCAT('%', :filtro, '%')) and (:ufEstado IS NULL OR me.ufEstado = UPPER(:ufEstado)))")
    Page<MunicipioEstado> buscarMunicipios(@Param("filtro") String filtro, @Param("ufEstado") String ufEstado, Pageable paginacao);
}
