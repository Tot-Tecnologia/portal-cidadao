package br.tec.tot.dardani.portal_cidadao.application.mappers;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import br.tec.tot.dardani.portal_cidadao.domain.models.Arquivo;
import br.tec.tot.dardani.portal_cidadao.domain.models.TipoArquivoEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArquivoRequestMapper {

    public Arquivo toDomain(MultipartFile arquivo) {
        log.debug("Mapeando MultiPartfile para classe de dominio");
        try {
            var dominio = Arquivo.criar(
                    null,
                    arquivo.getOriginalFilename(),
                    arquivo.getContentType(),
                    arquivo.getBytes(),
                    arquivo.getSize(),
                    TipoArquivoEnum.DOCUMENTO);
            log.info("Arquivo mapeado e validado");
            return dominio;
        } catch (IOException e) {
            throw new DomainException("Não foi possível ler o arquivo " + arquivo.getOriginalFilename());
        }
    }

}