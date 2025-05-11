package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.domain.models.Arquivo;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ArquivoEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArquivoMapper {

    public ArquivoEntity toEntity(Arquivo modelo) {

        var entidade = new ArquivoEntity();
        entidade.setConteudo(modelo.getConteudo());
        entidade.setId(modelo.getId());
        entidade.setMimeType(modelo.getMimeType());
        entidade.setNomeOriginal(modelo.getNomeOriginal());
        entidade.setTamanho(modelo.getTamanho());
        entidade.setTipoArquivo(modelo.getTipoArquivo());

        return entidade;
    }

    public Arquivo toModel(ArquivoEntity entidade) {
        return Arquivo.criar(entidade.getId(), entidade.getNomeOriginal(), entidade.getMimeType(),
                entidade.getConteudo(), entidade.getTamanho(), entidade.getTipoArquivo());
    }

}