package jrb.testetecnico.attus.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_municipio_estado")
public class MunicipioEstado extends ModeloBase {

    private String nomeMunicipio;

    private String nomeEstado;

    private String ufEstado;
}
