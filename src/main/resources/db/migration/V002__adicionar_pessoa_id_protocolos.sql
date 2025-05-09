ALTER TABLE protocolos 
    ADD COLUMN pessoa_id BIGINT NOT NULL;

ALTER TABLE protocolos 
    ADD CONSTRAINT FK_protocolos_pessoas 
    FOREIGN KEY (pessoa_id) 
    REFERENCES pessoas (id);

CREATE INDEX idx_protocolos_pessoa ON protocolos (pessoa_id);
