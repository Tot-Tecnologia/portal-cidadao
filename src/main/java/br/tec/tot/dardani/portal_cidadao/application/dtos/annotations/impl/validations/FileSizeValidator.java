package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations;

import java.util.ArrayList;
import java.util.List;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.domain.FileMetadata;

public class FileSizeValidator implements FileValidator {

    private final long maxSizeBytes;

    public FileSizeValidator(long maxSizeBytes) {
        this.maxSizeBytes = maxSizeBytes;
    }

    @Override
    public List<String> validate(List<FileMetadata> files) {
        List<String> errors = new ArrayList<>();
        for (FileMetadata file : files) {
            if (file.getSizeBytes() > maxSizeBytes) {
                errors.add(String.format(
                        "Arquivo '%s' (%.2f MB) excede o limite de %.2f MB",
                        file.getName(),
                        bytesToMB(file.getSizeBytes()),
                        bytesToMB(maxSizeBytes)));
            }
        }
        return errors;
    }

    private double bytesToMB(long bytes) {
        return bytes / 1024.0 / 1024.0;
    }
}
