package jrb.testetecnico.attus.domain.dto;

import jrb.testetecnico.attus.domain.model.PessoaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PessoaDto {

    private UUID id;

    private String nomeCompleto;

    private LocalDate dataNascimento;

    private List<EnderecoDto> enderecos;

    public static PessoaDto toDto(PessoaModel pessoa){
        return PessoaDto
                .builder()
                .nomeCompleto(pessoa.getNomeCompleto())
                .id(pessoa.getUuid())
                .dataNascimento(pessoa.getDataNascimento())
                .enderecos(pessoa.getEnderecos().stream().map(EnderecoDto::toDto).toList())
                .build();
    }
}
