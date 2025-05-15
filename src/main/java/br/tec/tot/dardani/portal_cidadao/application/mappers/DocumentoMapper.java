package br.tec.tot.dardani.portal_cidadao.application.mappers;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.domain.models.Documento;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.DocumentoEntity;

@Component
public class DocumentoMapper {

    public Documento toDomain(MultipartFile arquivo) {

        try {
            return new Documento(null,
                    arquivo.getOriginalFilename(),
                    arquivo.getContentType(),
                    arquivo.getBytes(),
                    arquivo.getSize());
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }

    }

    public DocumentoEntity toEntity(Documento documento) {
        var entity = new DocumentoEntity();

        entity.setConteudo(documento.getConteudo());
        entity.setMimeType(documento.getMimeType());
        entity.setTamanho(documento.getTamanho());
        entity.setNomeOriginal(documento.getNomeOriginal());

        return entity;
    }

}
