package jrb.testetecnico.attus.controller;

import jrb.testetecnico.attus.domain.dto.EstadoDto;
import jrb.testetecnico.attus.domain.dto.MunicipioDto;
import jrb.testetecnico.attus.service.estadomunicipio.MunicipioEstadoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("util")
public class UtilController {

    @Autowired
    private MunicipioEstadoService municipioEstadoService;


    @GetMapping("/municipios")
    public ResponseEntity<Page<MunicipioDto>> buscarMunicipios(@RequestParam(required = false, name = "filtro") String filtro,
                                                               @RequestParam(required = false, name = "ufEstado") String ufEstado,
                                                               @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){

        Page<MunicipioDto> municipios = municipioEstadoService.buscarMunicipios(filtro, ufEstado, paginacao);

        return ResponseEntity.ok(municipios);

    }

    @GetMapping("/estados")
    public ResponseEntity<Page<EstadoDto>> buscarMunicipios(@RequestParam(required = false, name = "filtro") String filtro,
                                                               @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){

        Page<EstadoDto> estados = municipioEstadoService.buscarEstados(filtro, paginacao);

        return ResponseEntity.ok(estados);

    }

}
