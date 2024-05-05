package jrb.testetecnico.attus.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jrb.testetecnico.attus.model.EnderecoModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record PessoaForm(
        @NotNull
        @NotBlank
        String nomeCompleto,

        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataNascimento,
        EnderecoModel enderecoPrincipal,
        List<EnderecoModel> enderecos
) {
}
