package br.tec.tot.dardani.portal_cidadao.application.mappers;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.domain.models.Guia;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.GuiaEntity;

@Component
public class GuiaMapper {

    public Guia toDomain(MultipartFile arquivo) {

        try {
            return new Guia(null,
                    arquivo.getOriginalFilename(),
                    arquivo.getContentType(),
                    arquivo.getBytes(),
                    arquivo.getSize());
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }

    }

    public GuiaEntity toEntity(Guia guia) {
        var entity = new GuiaEntity();

        entity.setConteudo(guia.getConteudo());
        entity.setMimeType(guia.getMimeType());
        entity.setTamanho(guia.getTamanho());
        entity.setNomeOriginal(guia.getNomeOriginal());
        entity.setStatus(guia.getStatus());

        return entity;
    }

}
