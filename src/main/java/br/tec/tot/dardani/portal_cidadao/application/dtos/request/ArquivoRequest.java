package br.tec.tot.dardani.portal_cidadao.application.dtos.request;

import java.util.Collection;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArquivoRequest {

    private Collection<MultipartFile> arquivos;

    public Collection<MultipartFile> getArquivos() {
        if (arquivos == null) {
            return List.of();
        }
        return arquivos;
    }

}
