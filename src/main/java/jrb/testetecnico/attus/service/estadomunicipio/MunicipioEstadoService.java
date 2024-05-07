package jrb.testetecnico.attus.service.estadomunicipio;

import jrb.testetecnico.attus.domain.dto.EstadoDto;
import jrb.testetecnico.attus.domain.dto.MunicipioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MunicipioEstadoService {

    Page<MunicipioDto> buscarMunicipios(String filtro, String ufEstado, Pageable paginacao);
    Page<EstadoDto> buscarEstados(String filtro, Pageable paginacao);


}
