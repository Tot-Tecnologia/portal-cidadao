package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import java.time.LocalDateTime;

import br.tec.tot.dardani.portal_cidadao.domain.models.TipoDocumento;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;
import lombok.Getter;

@Getter
public class ProtocoloFiltroResponse {

    private Long id;
    private String numeroProtocolo;
    private Long tipoDocumento;
    private String tipoDocumentoTexto;
    private LocalDateTime dataSolicitacao;
    private String statusEnum;
    private String statusTexto;

    public static ProtocoloFiltroResponse fromEntity(ProtocoloEntity entity) {

        var response = new ProtocoloFiltroResponse();

        response.id = entity.getId();
        response.numeroProtocolo = entity.getNumeroProtocolo();
        response.tipoDocumento = entity.getTipoDocumento();
        response.tipoDocumentoTexto = TipoDocumento.buscarDocumentos().stream()
                .filter(td -> td.getId().equals(entity.getTipoDocumento())).findFirst().get().getNome();
        response.dataSolicitacao = entity.getCriadoEm();
        response.statusEnum = entity.getStatus().name();
        response.statusTexto = entity.getStatus().getDescricao();

        return response;
    }

}