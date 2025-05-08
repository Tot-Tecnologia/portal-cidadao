package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEmbeddable {

    @Column(name = "endereco_logradouro", length = 100, nullable = false)
    private String logradouro;

    @Column(name = "endereco_numero", length = 10, nullable = false)
    private String numero;

    @Column(name = "endereco_bairro", length = 50, nullable = false)
    private String bairro;

    @Column(name = "endereco_complemento", length = 50)
    private String complemento;

    @Column(name = "endereco_cidade", length = 50, nullable = false)
    private String cidade;

    @Column(name = "endereco_estado", length = 2, nullable = false)
    private String estado;

    @Column(name = "endereco_cep", length = 9, nullable = false)
    private String cep;
}