package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.specifications;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;

import jakarta.persistence.criteria.Predicate;

public class ProtocoloSpecifications {
    public static Specification<ProtocoloEntity> consultarProtocolos(ProtocoloFiltrosRequest filtros) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            query.multiselect(
                    root.get("id"),
                    root.get("numeroProtocolo"),
                    root.get("tipoDocumento"),
                    root.get("criadoEm"),
                    root.get("status"));

            if (filtros.getAno() != null) {
                predicates.add(cb.equal(
                        cb.function("date_part", Integer.class,
                                cb.literal("year"),
                                root.get("criadoEm")),
                        filtros.getAno()));
            }

            if (Strings.isNotBlank(filtros.getNumeroProtocolo())) {
                predicates.add(cb.equal(root.get("numeroProtocolo"), filtros.getNumeroProtocolo()));
            }

            if (filtros.getTipoDocumento() != null) {
                predicates.add(cb.equal(root.get("tipoDocumento"), filtros.getTipoDocumento()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
