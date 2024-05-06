package jrb.testetecnico.attus.service.municipioscsv;

import jrb.testetecnico.attus.domain.dto.EstadoCSVDto;

import java.util.ArrayList;
import java.util.List;

public class ProcessadorCSVEstado implements ProcessadorCSV<EstadoCSVDto> {

    private final List<EstadoCSVDto> estados = new ArrayList<>();
    @Override
    public void processarLinha(String linhaCSV) {
        String[] splitLinha = linhaCSV.split(";");

        Long codigoIBGE = Long.parseLong(splitLinha[0]);
        String nomeEstado = splitLinha[1];
        String UF = splitLinha[2];

        this.estados.add(new EstadoCSVDto(codigoIBGE, UF, nomeEstado));

    }

    @Override
    public List<EstadoCSVDto> getLinhasProcessadas() {
        return this.estados;
    }
}
