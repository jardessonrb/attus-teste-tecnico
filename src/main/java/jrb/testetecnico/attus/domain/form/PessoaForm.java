package jrb.testetecnico.attus.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Builder
public record PessoaForm(
        @NotNull
        @NotBlank
        String nomeCompleto,

        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataNascimento,

        EnderecoForm enderecoPrincipal,
        List<EnderecoForm> enderecos
) {

        public PessoaForm(String nomeCompleto, LocalDate dataNascimento) {
                this(nomeCompleto, dataNascimento, null, null);
        }
}
