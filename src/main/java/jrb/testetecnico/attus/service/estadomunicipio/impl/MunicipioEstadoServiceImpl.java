package jrb.testetecnico.attus.service.estadomunicipio.impl;

import jrb.testetecnico.attus.domain.dto.EstadoDto;
import jrb.testetecnico.attus.domain.dto.MunicipioDto;
import jrb.testetecnico.attus.domain.model.MunicipioEstado;
import jrb.testetecnico.attus.domain.repository.EstadoRepository;
import jrb.testetecnico.attus.domain.repository.MunicipioRepository;
import jrb.testetecnico.attus.service.estadomunicipio.MunicipioEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MunicipioEstadoServiceImpl implements MunicipioEstadoService {

    @Autowired
    private MunicipioRepository municipioEstadoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public Page<MunicipioDto> buscarMunicipios(String filtro, String ufEstado, Pageable paginacao) {
        Page<MunicipioEstado> municipios = municipioEstadoRepository.buscarMunicipios(filtro, ufEstado, paginacao);

        return municipios.map(MunicipioDto::toDto);
    }

    @Override
    public Page<EstadoDto> buscarEstados(String filtro, Pageable paginacao) {
        Page<MunicipioEstado> estados = estadoRepository.buscarEstadosPorFiltro(filtro, paginacao);

        return estados.map(EstadoDto::toDto);
    }
}
