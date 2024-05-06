package jrb.testetecnico.attus.utils.municipioscsv;

import jrb.testetecnico.attus.domain.dto.EstadoCSVDto;
import jrb.testetecnico.attus.domain.dto.MunicipioCSVDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessarMunicipiosEEstadosCSV {

    @Autowired
    private LeitorCSV leitorCSV;
    void processarMunicipiosEEstados(){
        String pathArquivoEstados = "csv/estados.csv";
        String pathArquivoMunicipios = "csv/municipios.csv";
        Map<String, EstadoCSVDto> estadosMapeadosPorUF = new HashMap<>();

        List<EstadoCSVDto> estados = leitorCSV.lerArquivoCSV(pathArquivoEstados, new ProcessadorCSVEstado());
        for(EstadoCSVDto estado : estados){
            estadosMapeadosPorUF.put(estado.UF(), estado);
        }

        List<MunicipioCSVDto> municipios = leitorCSV.lerArquivoCSV(pathArquivoMunicipios, new ProcessadorCSVMunicipio());

    }
}
