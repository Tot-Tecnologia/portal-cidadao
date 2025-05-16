package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.domain.FileMetadata;

public class FileExtensionValidator implements FileValidator {

    private final Set<String> allowedExtensions;

    public FileExtensionValidator(Set<String> allowedExtensions) {
        this.allowedExtensions = new HashSet<>();
        for (String ext : allowedExtensions) {
            this.allowedExtensions.add(ext.toLowerCase());
        }
    }

    @Override
    public List<String> validate(List<FileMetadata> files) {
        List<String> errors = new ArrayList<>();
        for (FileMetadata file : files) {
            if (!allowedExtensions.contains(file.getContentType())) {
                errors.add(String.format("Arquivo '%s' tem extensão não permitida", file.getName()));
            }
        }
        return errors;
    }
}
