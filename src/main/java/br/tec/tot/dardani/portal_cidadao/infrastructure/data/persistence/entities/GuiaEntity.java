package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities;

import java.time.LocalDateTime;

import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.enums.StatusGuiaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "guias")
@DiscriminatorValue("GUIA")
public class GuiaEntity extends ArquivoEntity {

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private StatusGuiaEnum status;

}