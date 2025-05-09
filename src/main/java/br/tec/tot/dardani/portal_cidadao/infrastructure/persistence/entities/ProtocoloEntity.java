package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.UpdateTimestamp;

import br.tec.tot.dardani.portal_cidadao.domain.models.ProtocoloStatus;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables.ContatoEmbeddable;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables.EnderecoEmbeddable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@FilterDef(name = "proprietario", parameters = { @ParamDef(name = "pessoaId", type = Long.class) })
@Filter(name = "proprietario", condition = "pessoa_id = :pessoaId")
@Table(name = "protocolos", indexes = @Index(name = "idx_protocolo_numero", columnList = "numero_protocolo"))
public class ProtocoloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 14)
    private String cpfCnpj;

    @Column(name = "nome_solicitante", nullable = false, length = 100)
    private String nomeSolicitante;

    @Column(name = "numero_protocolo", nullable = false, unique = true)
    private String numeroProtocolo;

    @Column(name = "tipo_documento", nullable = false)
    private Long tipoDocumento;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "protocolo_status")
    private ProtocoloStatus status;

    @Embedded
    private EnderecoEmbeddable endereco;

    @Embedded
    private ContatoEmbeddable contato;

    @OneToMany(mappedBy = "protocolo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ArquivoEntity> arquivos = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private PessoaEntity pessoa;

}