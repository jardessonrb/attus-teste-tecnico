package jrb.testetecnico.attus.domain.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Valid
@Builder
public record PessoaForm(

        @NotNull(message = "Nome completo não pode ser nulo")
        @NotBlank(message = "Nome completo não pode ser vazio")
        String nomeCompleto,

        @NotNull(message = "Data não pode ser nulo")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dataNascimento,

        EnderecoForm enderecoPrincipal,
        List<EnderecoForm> enderecos
) {

        public PessoaForm(String nomeCompleto, LocalDate dataNascimento) {
                this(nomeCompleto, dataNascimento, null, null);
        }
}
