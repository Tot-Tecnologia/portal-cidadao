package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.domain.models.Cep;
import br.tec.tot.dardani.portal_cidadao.domain.models.CidadeEstado;
import br.tec.tot.dardani.portal_cidadao.domain.models.Endereco;
import br.tec.tot.dardani.portal_cidadao.domain.models.Localizacao;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables.EnderecoEmbeddable;

@Component
public class EnderecoMapper {

    public EnderecoEmbeddable toEmbeddable(Endereco modelo) {
        return new EnderecoEmbeddable(
                modelo.getLocalizacao().getLogradouro(),
                modelo.getLocalizacao().getNumero(),
                modelo.getLocalizacao().getBairro(),
                modelo.getLocalizacao().getComplemento(),
                modelo.getCidadeEstado().getCidade(),
                modelo.getCidadeEstado().getEstado(),
                modelo.getCep().getValor());
    }

    public Endereco toModel(EnderecoEmbeddable embeddable) {
        var cep = new Cep(embeddable.getCep());
        var cidadeEstado = new CidadeEstado(embeddable.getCidade(), embeddable.getEstado());
        var localizacao = new Localizacao(embeddable.getLogradouro(), embeddable.getNumero(),
                embeddable.getBairro(), embeddable.getComplemento());

        return new Endereco(localizacao, cidadeEstado, cep);

    }

}
