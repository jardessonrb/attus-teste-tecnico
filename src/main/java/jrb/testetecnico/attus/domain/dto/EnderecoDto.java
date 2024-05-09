package jrb.testetecnico.attus.domain.dto;

import jrb.testetecnico.attus.domain.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnderecoDto {

    private UUID id;

    private String logradouro;

    private String cep;

    private Integer numero;

    private String cidade;

    private String estado;

    private Boolean isEnderecoPrincipal;

    public static EnderecoDto toDto(Endereco enderecoModel){
        return EnderecoDto
                .builder()
                .isEnderecoPrincipal(enderecoModel.getIsEnderecoPrincipal())
                .id(enderecoModel.getUuid())
                .logradouro(enderecoModel.getLogradouro())
                .cep(enderecoModel.getCep())
                .cidade(enderecoModel.getCidade())
                .estado(enderecoModel.getEstado())
                .numero(enderecoModel.getNumero())
                .build();
    }
}
