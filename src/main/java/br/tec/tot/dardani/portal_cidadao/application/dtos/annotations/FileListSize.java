package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.FileListSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileListSizeValidator.class)
@Documented
public @interface FileListSize {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long fileSize();

    long maxFilesSize();

    String[] allowedExtensions() default { "application/pdf" };
}
