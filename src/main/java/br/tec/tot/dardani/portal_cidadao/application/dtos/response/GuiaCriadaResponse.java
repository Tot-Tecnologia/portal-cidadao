package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import java.time.LocalDateTime;

public record GuiaCriadaResponse(
        Long id,
        String nome,
        String status,
        LocalDateTime dataPagamento,
        LocalDateTime criadaEm) {

}
