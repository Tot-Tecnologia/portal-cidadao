package br.tec.tot.dardani.portal_cidadao.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.tec.tot.dardani.portal_cidadao.application.dtos.response.CidadaoCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.mappers.CidadaoMapper;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import br.tec.tot.dardani.portal_cidadao.domain.models.Cidadao;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.CidadaoRepository;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.UsuarioRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.repositories.FirebaseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriarCidadaoUseCase {

    private final CidadaoMapper mapper;
    private final CidadaoRepository cidadaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final FirebaseRepository firebaseRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public CidadaoCriadoResponse executar(Cidadao modelo) {

        var usuarioEncontrado = usuarioRepository.buscarUsuario(
                modelo.getEmail(),
                modelo.getCpfCnpj());

        if (usuarioEncontrado.isPresent()) {
            throw new DomainException("Já existe um usuário com esses dados");
        }

        var entitidade = mapper.toEntity(modelo);

        var cidadaoCriado = cidadaoRepository.salvar(entitidade);

        firebaseRepository.criarUsuario(modelo, cidadaoCriado.getId());

        return CidadaoCriadoResponse.fromEntity(entitidade);
    }

}
