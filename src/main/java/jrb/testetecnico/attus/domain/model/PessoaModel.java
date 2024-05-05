package jrb.testetecnico.attus.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @OneToOne
    private EnderecoModel enderecoPrincipal;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id")
    private List<EnderecoModel> enderecos;
}
