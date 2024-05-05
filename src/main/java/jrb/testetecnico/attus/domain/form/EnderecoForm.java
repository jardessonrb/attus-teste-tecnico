package jrb.testetecnico.attus.domain.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoForm(
        @NotNull
        @NotBlank
        String logradouro,

        @NotNull
        @NotBlank
        String cep,

        @NotNull
        @NotBlank
        String cidade,

        @NotNull
        @NotBlank
        String estado,

        @Min(0)
        Integer numero

) {
}
