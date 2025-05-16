package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.mappers;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.DocumentoEntity;

@Component
public class DocumentoMapper {

    public DocumentoEntity toEntity(MultipartFile arquivo) {

        try {
            var entity = new DocumentoEntity();

            entity.setConteudo(arquivo.getBytes());
            entity.setMimeType(arquivo.getContentType());
            entity.setNomeOriginal(arquivo.getOriginalFilename());
            entity.setTamanho(arquivo.getSize());

            return entity;

        } catch (IOException e) {

            throw new ApiException(e.getMessage());
        }

    }

}
