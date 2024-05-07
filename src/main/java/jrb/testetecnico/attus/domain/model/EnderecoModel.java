package jrb.testetecnico.attus.domain.model;

import jakarta.persistence.*;
import jrb.testetecnico.attus.domain.form.EnderecoForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_endereco")
public class EnderecoModel extends ModeloBase {

    private String logradouro;

    private String cep;

    private Integer numero;

    private String cidade;

    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", insertable = false, updatable = false)
    private PessoaModel pessoa;

    public static EnderecoModel toModel(EnderecoForm enderecoForm){
        return EnderecoModel
                .builder()
                .cep(enderecoForm.cep())
                .estado(enderecoForm.estado())
                .numero(enderecoForm.numero())
                .logradouro(enderecoForm.logradouro())
                .cidade(enderecoForm.cidade())
                .build();
    }
}
