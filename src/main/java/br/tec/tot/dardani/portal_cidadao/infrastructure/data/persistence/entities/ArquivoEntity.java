package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "arquivos")
@DiscriminatorColumn(name = "tipo_arquivo", discriminatorType = DiscriminatorType.STRING)
public class ArquivoEntity extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String nomeOriginal;

    @Column(nullable = false, length = 100)
    private String mimeType;
    @Lob
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(nullable = false)
    private byte[] conteudo;

    @Column(nullable = false)
    private Long tamanho;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "protocolo_id")
    private ProtocoloEntity protocolo;

}