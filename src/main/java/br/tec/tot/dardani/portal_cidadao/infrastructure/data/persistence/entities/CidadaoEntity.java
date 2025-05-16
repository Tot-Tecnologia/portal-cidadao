package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cidadaos")
@DiscriminatorValue("CIDADAO")
public class CidadaoEntity extends UsuarioEntity {

}
