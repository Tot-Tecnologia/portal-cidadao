package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import br.tec.tot.dardani.portal_cidadao.domain.models.ProtocoloStatusEnum;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables.ContatoEmbeddable;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables.EnderecoEmbeddable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
public class ProtocoloEntity extends BaseEntity {

    @Column(nullable = false, length = 14)
    private String cpfCnpj;

    @Column(name = "nome_solicitante", nullable = false, length = 100)
    private String nomeSolicitante;

    @Column(name = "numero_protocolo", nullable = false, unique = true)
    private String numeroProtocolo;

    @Column(name = "tipo_documento", nullable = false)
    private Long tipoDocumento;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "protocolo_status")
    private ProtocoloStatusEnum status;

    @Embedded
    private EnderecoEmbeddable endereco;

    @Embedded
    private ContatoEmbeddable contato;

    @OneToMany(mappedBy = "protocolo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<GuiaEntity> guias = new ArrayList<>();

    @OneToMany(mappedBy = "protocolo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DocumentoEntity> documentos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private PessoaEntity pessoa;

    public void adicionarGuia(GuiaEntity guiaEntity) {
        getGuias().add(guiaEntity);
    }

    public List<GuiaEntity> getGuias() {
        if (guias == null) {
            return List.of();
        }
        return guias;
    }

    public void adicionarDocumento(DocumentoEntity documentoEntity) {
        getDocumentos().add(documentoEntity);
    }

    public List<DocumentoEntity> getDocumentos() {
        if (documentos == null) {
            return List.of();
        }
        return documentos;
    }

}