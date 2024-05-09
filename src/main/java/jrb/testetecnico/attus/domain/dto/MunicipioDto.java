package jrb.testetecnico.attus.domain.dto;

import jrb.testetecnico.attus.domain.model.MunicipioEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MunicipioDto {
    private String nomeMunicipio;
    private String ufEstado;

    public static MunicipioDto toDto(MunicipioEstado municipioEstadoModel){
        return new MunicipioDto(municipioEstadoModel.getNomeMunicipio(), municipioEstadoModel.getUfEstado());
    }
}
