package jrb.testetecnico.attus.domain.dto;

import jrb.testetecnico.attus.domain.model.MunicipioEstadoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstadoDto {

    private String nomeEstado;

    private String ufEstado;

    public static EstadoDto toDto(MunicipioEstadoModel municipioEstadoModel){
        return new EstadoDto(municipioEstadoModel.getNomeEstado(), municipioEstadoModel.getUfEstado());
    }

}
