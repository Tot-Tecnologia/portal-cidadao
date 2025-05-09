package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.tec.tot.dardani.portal_cidadao.application.domain.Paginacao;

public abstract class AbstractRepository {

    protected Pageable parsePaginacao(Paginacao paginacao) {
            Integer paginaAtual = paginacao.getPaginaAtual();
            if (paginaAtual == null) {
                paginaAtual = 1; 
            }
                Integer itensPagina = paginacao.getItensPagina();
            if (itensPagina == null) {
                itensPagina = 10; 
            }
            int offset = (paginaAtual - 1) * itensPagina;

            return PageRequest.of(
                    paginaAtual - 1,  
                    itensPagina,
                    parseSort(paginacao.getSort()));
        }

    private Sort parseSort(String[] sort) {
        if (sort.length >= 2) {
            return Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);
        } else if (sort.length == 1) {
            return Sort.by(sort[0]);
        }
        return Sort.unsorted();
    }
}