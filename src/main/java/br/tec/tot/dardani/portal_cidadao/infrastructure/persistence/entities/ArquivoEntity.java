package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import br.tec.tot.dardani.portal_cidadao.domain.models.TipoArquivoEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "arquivos")
@Getter
@Setter
public class ArquivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_arquivo", nullable = false)
    private TipoArquivoEnum tipoArquivo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "protocolo_id")
    private ProtocoloEntity protocolo;

}