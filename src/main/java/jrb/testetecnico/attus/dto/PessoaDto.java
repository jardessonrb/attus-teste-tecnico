package jrb.testetecnico.attus.dto;

import jakarta.persistence.*;
import jrb.testetecnico.attus.model.EnderecoModel;
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
}
