package jrb.testetecnico.attus.utils.municipioscsv;

import jrb.testetecnico.attus.domain.dto.EstadoCSVDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProcessadorCSVEstado implements ProcessadorCSVStrategy<EstadoCSVDto> {
    private static final Logger logger = LoggerFactory.getLogger(ProcessadorCSVEstado.class);

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
