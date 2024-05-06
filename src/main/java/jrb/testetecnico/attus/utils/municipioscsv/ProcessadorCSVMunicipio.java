package jrb.testetecnico.attus.service.municipioscsv;

import jrb.testetecnico.attus.domain.dto.MunicipioCSVDto;

import java.util.ArrayList;
import java.util.List;

public class ProcessadorCSVMunicipio implements ProcessadorCSV<MunicipioCSVDto> {

    private final List<MunicipioCSVDto> estados = new ArrayList<>();
    @Override
    public void processarLinha(String linhaCSV) {
        String[] splitLinha = linhaCSV.split(";");

        Long codigoIBGE = Long.parseLong(splitLinha[2]);
        String ufEstado = splitLinha[3];
        String nomeMunicipio = splitLinha[4];

        this.estados.add(new MunicipioCSVDto(codigoIBGE, nomeMunicipio, ufEstado));

    }

    @Override
    public List<MunicipioCSVDto> getLinhasProcessadas() {
        return this.estados;
    }
}
