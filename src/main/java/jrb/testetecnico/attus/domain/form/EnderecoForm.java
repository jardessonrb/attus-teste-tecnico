package jrb.testetecnico.attus.domain.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoForm(

        @NotBlank(message = "Logradouro é um campo obrigatório")
        String logradouro,

        @Pattern(regexp = "\\d{8}",
                message
                        = "O CEP é composto por 8 digitos números")
        @NotBlank(message = "CEP é um campo obrigatório")
        String cep,

        @NotBlank(message = "Estado é um campo obrigatório")
        String cidade,

        @NotBlank(message = "Estado é um campo obrigatório")
        String estado,

        @Min(1)
        Integer numero

) {
}
