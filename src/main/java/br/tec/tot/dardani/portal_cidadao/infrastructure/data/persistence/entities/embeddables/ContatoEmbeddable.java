package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContatoEmbeddable {

    @Column(name = "contato_telefone", length = 11, nullable = false)
    private String telefone;

    @Column(name = "contato_email", length = 100, nullable = false, unique = true)
    private String email;
}