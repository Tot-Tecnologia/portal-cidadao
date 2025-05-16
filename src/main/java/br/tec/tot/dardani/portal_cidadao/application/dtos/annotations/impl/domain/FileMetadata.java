package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.domain;

public class FileMetadata {
    private final String name;
    private final long sizeBytes;
    private final String contentType;

    public FileMetadata(String name, long sizeBytes, String contentType) {
        this.name = name;
        this.sizeBytes = sizeBytes;
        this.contentType = contentType != null ? contentType.toLowerCase() : "";
    }

    public String getName() {
        return name;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public String getContentType() {
        return contentType;
    }
}
