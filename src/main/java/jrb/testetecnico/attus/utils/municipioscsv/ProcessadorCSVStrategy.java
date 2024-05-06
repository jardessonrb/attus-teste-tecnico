package jrb.testetecnico.attus.utils.municipioscsv;

import java.util.List;

public interface ProcessadorCSV<T> {
    void processarLinha(String linhaCSV);
    List<T> getLinhasProcessadas();
}
