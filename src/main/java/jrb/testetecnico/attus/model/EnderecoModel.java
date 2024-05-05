package jrb.testetecnico.attus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_endereco")
public class EnderecoModel extends ModeloBase {

    private String logradouro;

    private String cep;

    private Integer numero;

    private String cidade;

    private String estado;

//    @OneToOne(mappedBy = "pessoa")
//    private PessoaModel pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", insertable = false, updatable = false)
    private PessoaModel pessoa;
}
