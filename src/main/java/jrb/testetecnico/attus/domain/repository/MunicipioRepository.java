package jrb.testetecnico.attus.domain.repository;

import jrb.testetecnico.attus.domain.model.MunicipioEstadoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MunicipioEstadoRepository extends JpaRepository<MunicipioEstadoModel, Long> {

    @Query("SELECT me FROM MunicipioEstadoModel me WHERE ((:filtro is null or  me.nomeMunicipio ILIKE CONCAT('%', :filtro, '%')) and (:ufEstado IS NULL OR me.ufEstado = UPPER(:ufEstado)))")
    Page<MunicipioEstadoModel> buscarMunicipios(@Param("filtro") String filtro, @Param("ufEstado") String ufEstado, Pageable paginacao);

    @Query("SELECT me FROM MunicipioEstadoModel me WHERE (:filtro is null or  me.nomeEstado ILIKE CONCAT('%', :filtro, '%') or me.ufEstado = UPPER(:filtro))")
    Page<MunicipioEstadoModel> buscarEstados(@Param("filtro") String filtro, Pageable paginacao);
}
