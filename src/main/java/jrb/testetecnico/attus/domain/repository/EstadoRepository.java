package jrb.testetecnico.attus.domain.repository;

import jrb.testetecnico.attus.domain.model.MunicipioEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class EstadoRepositoryParse implements RowMapper<MunicipioEstado> {
    @Override
    public MunicipioEstado mapRow(ResultSet rs, int rowNum) throws SQLException {
        MunicipioEstado municipioEstadoModel = MunicipioEstado
                .builder()
                .nomeEstado(rs.getString("nome_estado"))
                .ufEstado(rs.getString("uf_estado"))
                .build();

        return municipioEstadoModel;
    }
}

class EstadoRepositoryLongParse implements RowMapper<Long> {
    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Long.parseLong(rs.getString("quantidade_estados_encontrados"));
    }
}

@Repository
public class EstadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Page<MunicipioEstado> buscarEstadosPorFiltro(String filtro, Pageable paginacao) {
        Object[] parametros = {filtro, filtro, filtro};

        String query = "SELECT nome_estado, uf_estado FROM tb_municipio_estado \n" +
                "WHERE (? is null or LOWER(nome_estado) LIKE '%' || LOWER(?) || '%' or uf_estado = UPPER(?)) \n" +
                "GROUP BY nome_estado, uf_estado";
        List<MunicipioEstado> estados = jdbcTemplate.query(query, new EstadoRepositoryParse(), parametros);
        Long quantidadeDeEstadosEncontrados = countEstadosPorBusca(filtro);

        return new PageImpl(estados, paginacao, quantidadeDeEstadosEncontrados);
    }

    private Long countEstadosPorBusca(String filtro){
        Object[] parametros = {filtro, filtro, filtro};
        String query = "SELECT count(distinct uf_estado) as quantidade_estados_encontrados FROM tb_municipio_estado WHERE (? is null or  nome_estado ILIKE CONCAT('%', ?, '%') or uf_estado = UPPER(?))";

        return jdbcTemplate.queryForObject(query, new EstadoRepositoryLongParse(), parametros);
    }


}
