package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities;

import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.embeddables.ContatoEmbeddable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoas")
@DiscriminatorColumn(name = "tipo_pessoa", discriminatorType = DiscriminatorType.STRING)
public abstract class PessoaEntity extends BaseEntity {

    @Column(nullable = false, length = 100, name = "nome")
    private String nome;

    @Column(name = "documento", unique = true)
    private String documento;

    @Embedded
    private ContatoEmbeddable contato;
}