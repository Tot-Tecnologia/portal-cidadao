package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.FileListSize;
import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.domain.FileMetadata;
import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations.FileExtensionValidator;
import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations.FileSizeValidator;
import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations.FileTotalSizeValidator;
import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations.FileValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileListSizeValidator implements ConstraintValidator<FileListSize, Collection<MultipartFile>> {

    private long maxFileSizeBytes;
    private long maxTotalSizeMB;
    private List<String> allowedExtensions;

    private List<FileValidator> validators;

    @Override
    public void initialize(FileListSize constraintAnnotation) {
        this.maxFileSizeBytes = constraintAnnotation.fileSize();
        this.maxTotalSizeMB = constraintAnnotation.maxFilesSize();
        this.allowedExtensions = List.of(constraintAnnotation.allowedExtensions());

        this.validators = List.of(
                new FileSizeValidator(maxFileSizeBytes),
                new FileExtensionValidator(Set.copyOf(allowedExtensions)),
                new FileTotalSizeValidator(maxTotalSizeMB));
    }

    @Override
    public boolean isValid(Collection<MultipartFile> files, ConstraintValidatorContext context) {
        if (files == null || files.isEmpty()) {
            return true;
        }

        List<FileMetadata> domainFiles = files.stream()
                .filter(f -> f != null)
                .map(f -> new FileMetadata(f.getOriginalFilename(), f.getSize(), f.getContentType()))
                .collect(Collectors.toList());

        List<String> errors = new ArrayList<>();
        for (FileValidator validator : validators) {
            errors.addAll(validator.validate(domainFiles));
        }

        if (!errors.isEmpty()) {
            context.disableDefaultConstraintViolation();
            errors.forEach(msg -> context.buildConstraintViolationWithTemplate(msg).addConstraintViolation());
            return false;
        }

        return true;
    }
}
