package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations;

import java.util.List;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.domain.FileMetadata;

public interface FileValidator {
    List<String> validate(List<FileMetadata> files);
}
