package br.tec.tot.dardani.portal_cidadao.application.usecases;

import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.CidadaoRequest;
import br.tec.tot.dardani.portal_cidadao.application.gateway.CidadaoGateway;
import br.tec.tot.dardani.portal_cidadao.application.gateway.UsuarioGateway;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import br.tec.tot.dardani.portal_cidadao.domain.models.Cidadao;
import br.tec.tot.dardani.portal_cidadao.domain.models.Contato;
import br.tec.tot.dardani.portal_cidadao.domain.models.CpfCnpj;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriarCidadaoUseCase {

    private final CidadaoGateway gateway;
    private final UsuarioGateway usuarioGateway;

    public Cidadao executar(CidadaoRequest request) {

        var usuarioEncontrado = usuarioGateway.buscarPessoa(request.email(), request.cpfCnpj());

        if (usuarioEncontrado.isPresent()) {
            throw new DomainException("Já existe um usuário com esses dados");
        }

        var modelo = new Cidadao(request.nome(), request.senha(), request.email(), new CpfCnpj(request.cpfCnpj()),
                new Contato(request.telefone(), request.email()));

        return gateway.criarUsuario(modelo);
    }

}
