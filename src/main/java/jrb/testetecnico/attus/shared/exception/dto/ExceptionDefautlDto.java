package jrb.testetecnico.attus.shared.exception.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDefautlDto {

    private String mensagem;
    private Integer statusCode;
    private String pathRequest;

}
