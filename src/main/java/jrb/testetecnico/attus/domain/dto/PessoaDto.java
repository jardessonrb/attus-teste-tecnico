package jrb.testetecnico.attus.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PessoaDto {

    private UUID id;

    private String nomeCompleto;

    private LocalDate dataNascimento;
}
