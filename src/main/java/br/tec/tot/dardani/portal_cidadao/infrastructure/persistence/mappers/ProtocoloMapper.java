package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.domain.models.Arquivo;
import br.tec.tot.dardani.portal_cidadao.domain.models.CpfCnpj;
import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ArquivoEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProtocoloMapper {

    private final ContatoMapper contatoMapper;
    private final EnderecoMapper enderecoMapper;
    private final ArquivoMapper arquivoMapper;

    public ProtocoloEntity toEntity(Protocolo modelo) {
        ProtocoloEntity entidade = new ProtocoloEntity();

        mapearAtributosBasicos(modelo, entidade);
        mapearObjetosEmbeddables(modelo, entidade);
        mapearRelacionamentos(modelo, entidade);

        return entidade;
    }

    public Protocolo toModel(ProtocoloEntity entidade) {
        return criarProtocoloAPartirDaEntidade(entidade);
    }

    private void mapearAtributosBasicos(Protocolo modelo, ProtocoloEntity entidade) {
        entidade.setCpfCnpj(extrairValorCpfCnpj(modelo));
        entidade.setNomeSolicitante(modelo.getNomeSolicitante());
        entidade.setNumeroProtocolo(modelo.getNumeroProtocolo());
        entidade.setDescricao(modelo.getDescricao());
        entidade.setStatus(modelo.getStatus());
        entidade.setTipoDocumento(modelo.getTipoDocumento());
    }

    private String extrairValorCpfCnpj(Protocolo modelo) {
        return modelo.getCpfCnpj() != null ? modelo.getCpfCnpj().getValor() : null;
    }

    private void mapearObjetosEmbeddables(Protocolo modelo, ProtocoloEntity entidade) {
        entidade.setContato(contatoMapper.toContatoEmbeddable(modelo.getContato()));
        entidade.setEndereco(enderecoMapper.toEmbeddable(modelo.getEndereco()));
    }

    private void mapearRelacionamentos(Protocolo modelo, ProtocoloEntity entidade) {
        if (modelo.getArquivos() != null && !modelo.getArquivos().isEmpty()) {
            entidade.setArquivos(mapearListaArquivos(modelo.getArquivos(), entidade));
        }
    }

    private List<ArquivoEntity> mapearListaArquivos(Collection<Arquivo> arquivos, ProtocoloEntity protocoloEntity) {

        if (arquivos == null) {
            return List.of();
        }

        return arquivos.stream()
                .map(arquivoMapper::toEntity)
                .peek(arquivo -> arquivo.setProtocolo(protocoloEntity))
                .toList();
    }

    private Protocolo criarProtocoloAPartirDaEntidade(ProtocoloEntity entidade) {
        var protocolo = new Protocolo(
                entidade.getId(),
                criarCpfCnpj(entidade.getCpfCnpj()),
                entidade.getNomeSolicitante(),
                entidade.getDescricao(),
                entidade.getNumeroProtocolo(),
                enderecoMapper.toModel(entidade.getEndereco()),
                contatoMapper.toModel(entidade.getContato()),
                entidade.getStatus(),
                entidade.getTipoDocumento());

        if (entidade.getArquivos() == null) {
            entidade.setArquivos(List.of());
        }

        entidade.getArquivos().stream().map(arquivoMapper::toModel).forEach(protocolo::adicionarArquivo);

        return protocolo;
    }

    private CpfCnpj criarCpfCnpj(String valor) {
        return valor != null ? new CpfCnpj(valor) : null;
    }
}