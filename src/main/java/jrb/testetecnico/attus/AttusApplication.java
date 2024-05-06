package jrb.testetecnico.attus;

import jrb.testetecnico.attus.utils.municipioscsv.ProcessadorEstadosEMunicipiosCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AttusApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(AttusApplication.class);

	@Autowired
	private ProcessadorEstadosEMunicipiosCSV processadorEstadosEMunicipiosCSV;

	public static void main(String[] args) {
		SpringApplication.run(AttusApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Iniciando o processamento de estados e municípios");
		try{
			processadorEstadosEMunicipiosCSV.processarMunicipiosEEstados();
			logger.info("Sucesso: Finalizado o processamento de estados e municípos");
		}catch (Exception e){
			logger.error("Erro: Falha ao processar os estados e municípos");
			logger.error("Mensagem de erro: "+e.getMessage());

		}
	}
}
