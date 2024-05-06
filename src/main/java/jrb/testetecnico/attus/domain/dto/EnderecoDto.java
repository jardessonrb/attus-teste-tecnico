package jrb.testetecnico.attus.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnderecoDto {

    private String logradouro;

    private String cep;

    private Integer numero;

    private String cidade;

    private String estado;
}
