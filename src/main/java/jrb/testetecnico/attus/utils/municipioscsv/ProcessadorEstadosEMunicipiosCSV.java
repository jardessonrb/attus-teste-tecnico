package jrb.testetecnico.attus.utils.municipioscsv;

import jrb.testetecnico.attus.domain.dto.EstadoCSVDto;
import jrb.testetecnico.attus.domain.dto.MunicipioCSVDto;
import jrb.testetecnico.attus.domain.model.MunicipioEstadoModel;
import jrb.testetecnico.attus.domain.repository.MunicipioEstadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessadorEstadosEMunicipiosCSV {

    @Autowired
    private LeitorCSV leitorCSV;

    @Autowired
    private MunicipioEstadoRepository municipioEstadoRepository;

    public void processarMunicipiosEEstados(){
        String pathArquivoEstados = "csv/estados.csv";
        String pathArquivoMunicipios = "csv/municipios.csv";

        List<EstadoCSVDto> estados = leitorCSV.lerArquivoCSV(pathArquivoEstados, new ProcessadorCSVEstado());

        Map<String, EstadoCSVDto> estadosMapeados = toMapEstados(estados);

        List<MunicipioCSVDto> municipios = leitorCSV.lerArquivoCSV(pathArquivoMunicipios, new ProcessadorCSVMunicipio());

        for (MunicipioCSVDto municipio : municipios){
            EstadoCSVDto estado = estadosMapeados.get(municipio.ufEstado());
            MunicipioEstadoModel municipioEstadoModel = MunicipioEstadoModel
                    .builder()
                    .nomeMunicipio(municipio.nomeMunicipio())
                    .ufEstado(estado.UF())
                    .nomeEstado(estado.nomeEstado())
                    .build();

            municipioEstadoRepository.save(municipioEstadoModel);
        }


    }

    private Map<String, EstadoCSVDto> toMapEstados(List<EstadoCSVDto> estados){
        Map<String, EstadoCSVDto> estadosMapeadosPorUF = new HashMap<>();

        for(EstadoCSVDto estado : estados){
            estadosMapeadosPorUF.put(estado.UF(), estado);
        }

        return estadosMapeadosPorUF;
    }
}
