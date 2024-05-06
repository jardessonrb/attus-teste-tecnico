package jrb.testetecnico.attus.utils.municipioscsv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class LeitorCSV<T> {

    @Autowired
    private ResourceLoader resourceLoader;

    public List<T> lerArquivoCSV(String pathArquivoCSV, ProcessadorCSVStrategy<T> processadorCSV){
        try {
            Resource resource = resourceLoader.getResource("classpath:"+pathArquivoCSV);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.ISO_8859_1));
            CSVReader csvReader = new CSVReader(reader);

            String[] linha;
            csvReader.readNext(); //Descatar a primeira linha de cabeçalho
            while ((linha = csvReader.readNext()) != null) {
                processadorCSV.processarLinha(linha[0]);
            }

            csvReader.close();
            reader.close();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return processadorCSV.getLinhasProcessadas();
    }
}
