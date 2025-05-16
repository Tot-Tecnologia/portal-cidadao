package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations;

import java.util.List;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.domain.FileMetadata;

public class FileTotalSizeValidator implements FileValidator {

    private final double maxTotalSizeMB;

    public FileTotalSizeValidator(double maxTotalSizeMB) {
        this.maxTotalSizeMB = maxTotalSizeMB;
    }

    @Override
    public List<String> validate(List<FileMetadata> files) {
        long totalBytes = files.stream().mapToLong(FileMetadata::getSizeBytes).sum();
        double totalMB = bytesToMB(totalBytes);

        if (totalMB > maxTotalSizeMB) {
            return List.of(String.format("O tamanho total dos arquivos (%.2f MB) excede o limite permitido de %.2f MB",
                    totalMB, maxTotalSizeMB));
        }
        return List.of();
    }

    private double bytesToMB(long bytes) {
        return bytes / 1024.0 / 1024.0;
    }

}