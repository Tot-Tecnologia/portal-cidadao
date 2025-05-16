package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.mappers;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.GuiaEntity;

@Component
public class GuiaMapper {

    public GuiaEntity toEntity(MultipartFile arquivo) {

        try {
            var entity = new GuiaEntity();
            entity.setNomeOriginal(arquivo.getOriginalFilename());
            entity.setMimeType(arquivo.getContentType());
            entity.setTamanho(arquivo.getSize());
            entity.setConteudo(arquivo.getBytes());

            return entity;

        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }

    }

}
