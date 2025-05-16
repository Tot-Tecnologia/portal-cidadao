package br.tec.tot.dardani.portal_cidadao.application.dtos.request;

import java.util.Collection;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.FileListSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArquivoRequest {

    @FileListSize(fileSize = 2 * 1024 * 1024, maxFilesSize = 15, allowedExtensions = {
            "application/pdf" })
    private Collection<MultipartFile> documentos;

    @FileListSize(fileSize = 2 * 1024 * 1024, maxFilesSize = 15, allowedExtensions = {
            "application/pdf" })
    private Collection<MultipartFile> guias;

    public Collection<MultipartFile> getDocumentos() {
        if (documentos == null) {
            return List.of();
        }
        return documentos;
    }

    public Collection<MultipartFile> getGuias() {
        if (guias == null) {
            return List.of();
        }
        return guias;
    }

}
