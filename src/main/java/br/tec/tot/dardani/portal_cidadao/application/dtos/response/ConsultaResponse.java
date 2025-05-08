package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import java.util.Collection;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsultaResponse<T> {

    private Collection<T> data;
    private int paginaAtual;
    private int itensPagina;
    private boolean proximaPagina;
    private boolean paginaAnterior;
    private boolean ultimaPagina;
    private boolean primeiraPagina;
    private boolean vazio;

    public static <T> ConsultaResponse<T> of(Page<T> data) {
        return ConsultaResponse.<T>builder()
                .data(data.getContent())
                .paginaAtual(data.getNumber())
                .itensPagina(data.getSize())
                .proximaPagina(data.hasNext())
                .paginaAnterior(data.hasPrevious())
                .ultimaPagina(data.isLast())
                .primeiraPagina(data.isFirst())
                .vazio(data.isEmpty())
                .build();
    }

}
