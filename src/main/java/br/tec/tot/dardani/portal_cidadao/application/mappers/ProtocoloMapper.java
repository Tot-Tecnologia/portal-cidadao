package br.tec.tot.dardani.portal_cidadao.application.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.domain.models.Cep;
import br.tec.tot.dardani.portal_cidadao.domain.models.CidadeEstado;
import br.tec.tot.dardani.portal_cidadao.domain.models.Contato;
import br.tec.tot.dardani.portal_cidadao.domain.models.CpfCnpj;
import br.tec.tot.dardani.portal_cidadao.domain.models.Endereco;
import br.tec.tot.dardani.portal_cidadao.domain.models.Localizacao;
import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables.ContatoEmbeddable;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables.EnderecoEmbeddable;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProtocoloMapper {

    private final DocumentoMapper documentoMapper;

    public Protocolo toDomain(ProtocoloRequest request) {

        var protocolo = new Protocolo(
                new CpfCnpj(request.getCpfCnpj()),
                request.getNomeSolicitante(),
                request.getDescricao(),
                new Endereco(
                        new Localizacao(
                                request.getLogradouro(),
                                request.getNumero(),
                                request.getBairro(),
                                request.getComplemento()),
                        new CidadeEstado(
                                request.getCidade(),
                                request.getEstado()),
                        new Cep(request.getCep())),
                new Contato(request.getTelefone(), request.getEmail()),
                request.getTipoDocumento());

        request.getDocumentos()
                .stream().map(documentoMapper::toDomain)
                .forEach(protocolo::adicionarDocumento);

        return protocolo;
    }

    public ProtocoloEntity toEntity(Protocolo model) {
        var entity = new ProtocoloEntity();

        entity.setContato(new ContatoEmbeddable(model.getContato().getTelefone(), model.getContato().getEmail()));
        entity.setEndereco(new EnderecoEmbeddable(
                model.getEndereco().getLocalizacao().getLogradouro(),
                model.getEndereco().getLocalizacao().getNumero(),
                model.getEndereco().getLocalizacao().getBairro(),
                model.getEndereco().getLocalizacao().getComplemento(),
                model.getEndereco().getCidadeEstado().getCidade(),
                model.getEndereco().getCidadeEstado().getEstado(),
                model.getEndereco().getCep().getValor()));
        entity.setNomeSolicitante(model.getNomeSolicitante());
        entity.setDescricao(model.getDescricao());
        entity.setStatus(model.getStatus());
        entity.setTipoDocumento(model.getTipoDocumento());
        entity.setCpfCnpj(model.getCpfCnpj().getValor());
        entity.setNumeroProtocolo(model.getNumeroProtocolo());

        model.getDocumentos().stream()
                .map(documentoMapper::toEntity)
                .peek(doc -> doc.setProtocolo(entity))
                .forEach(entity::adicionarDocumento);

        return entity;

    }

}
