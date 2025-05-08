package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import java.time.LocalDateTime;

public record ProtocoloFiltroResponse(Long id, String numeroProtocolo, Long tipoDocumento,
                LocalDateTime dataSolicitacao,
                String status) {

}