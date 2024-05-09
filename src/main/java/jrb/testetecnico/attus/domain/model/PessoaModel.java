package jrb.testetecnico.attus.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_pessoa")
public class PessoaModel extends ModeloBase{
    private static final long serialVersionUID = 1L;

    private String nomeCompleto;

    private LocalDate dataNascimento;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id")
    private List<EnderecoModel> enderecos = new ArrayList<>();

    public EnderecoModel getEnderecoPrincipal(){
        return Objects.nonNull(this.enderecos) ? this.enderecos.stream().filter(endereco -> endereco.getIsEnderecoPrincipal()).findFirst().orElse(null) : null;
    }
}
