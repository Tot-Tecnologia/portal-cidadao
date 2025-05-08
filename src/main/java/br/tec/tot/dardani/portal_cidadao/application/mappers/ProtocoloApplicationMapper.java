package br.tec.tot.dardani.portal_cidadao.application.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ArquivoCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.domain.models.CpfCnpj;
import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProtocoloApplicationMapper {

        private final EnderecoRequestMapper enderecoMapper;
        private final ContatoRequestMapper contatoMapper;
        private final ArquivoRequestMapper arquivoMapper;

        public Protocolo toDomain(ProtocoloRequest request) {
                log.debug("Mapeando ProtocoloRequest para classe de dominio");
                Protocolo protocolo = new Protocolo(
                                new CpfCnpj(request.getCpfCnpj()),
                                request.getNomeSolicitante(),
                                request.getDescricao(),
                                enderecoMapper.toDomain(request),
                                contatoMapper.toDomain(request),
                                request.getTipoDocumento());

                if (request.getArquivos() == null) {
                        request.setArquivos(List.of());
                }

                request.getArquivos().stream()
                                .map(arquivoMapper::toDomain)
                                .forEach(protocolo::adicionarArquivo);
                log.info("Protocolo mapeado e validado");
                return protocolo;
        }

        public ProtocoloCriadoResponse toResponse(Protocolo protocolo) {
                return new ProtocoloCriadoResponse(protocolo.getId(), protocolo.getCpfCnpj().getValor(),
                                protocolo.getContato().getTelefone(), protocolo.getNomeSolicitante(),
                                protocolo.getEndereco().getLocalizacao().getLogradouro(),
                                protocolo.getEndereco().getLocalizacao().getNumero(),
                                protocolo.getEndereco().getLocalizacao().getBairro(),
                                protocolo.getEndereco().getCep().getValor(),
                                protocolo.getEndereco().getCidadeEstado().getEstado(),
                                protocolo.getContato().getEmail(),
                                protocolo.getEndereco().getLocalizacao().getComplemento(), protocolo.getDescricao(),
                                protocolo.getEndereco().getCidadeEstado().getCidade(),
                                protocolo.getTipoDocumento(), protocolo.getStatus().name(),
                                protocolo.getArquivos() != null ? protocolo.getArquivos().stream()
                                                .map(arq -> new ArquivoCriadoResponse(arq.getId(),
                                                                arq.getNomeOriginal()))
                                                .toList() : List.of());

        }
}
