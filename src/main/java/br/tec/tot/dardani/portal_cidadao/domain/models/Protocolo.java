package br.tec.tot.dardani.portal_cidadao.domain.models;

import java.util.ArrayList;
import java.util.Collection;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public class Protocolo {

    private final Long id;

    private final CpfCnpj cpfCnpj;
    private final Contato contato;
    private final Endereco endereco;

    private final String descricao;
    private final String nomeSolicitante;
    private final String numeroProtocolo;
    private final ProtocoloStatus status;
    private final Long tipoDocumento;
    private Collection<Arquivo> arquivos;

    public Protocolo(CpfCnpj cpfCnpj, String nomeSolicitante, String descricao,
            Endereco endereco, Contato contato, Long tipoDocumento) {

        this(null, cpfCnpj, nomeSolicitante, descricao, gerarProtocolo(), endereco, contato,
                ProtocoloStatus.EM_ANALISE, tipoDocumento);

    }

    public Protocolo(Long id, CpfCnpj cpfCnpj, String nomeSolicitante, String descricao, String numeroProtocolo,
            Endereco endereco, Contato contato, ProtocoloStatus status, Long tipoDocumento) {
        this.id = id;
        this.numeroProtocolo = numeroProtocolo;
        this.nomeSolicitante = validarNomeSolicitante(nomeSolicitante);
        this.descricao = validarDescricao(descricao);
        this.endereco = validarEndereco(endereco);
        this.contato = validarContato(contato);
        this.cpfCnpj = validarCpfCnpj(cpfCnpj);
        this.tipoDocumento = tipoDocumento;
        this.status = status;
    }

    public void adicionarArquivo(Arquivo arquivo) {
        if (this.arquivos == null) {
            this.arquivos = new ArrayList<>();
        }
        this.arquivos.add(arquivo);
    }

    public boolean isNovo() {
        return this.id == null;
    }

    private static String gerarProtocolo() {
        return "PR" + System.currentTimeMillis();
    }

    private String validarNomeSolicitante(String nomeSolicitante) {
        if (nomeSolicitante == null || nomeSolicitante.trim().isEmpty()) {
            throw new DomainException("Nome do solicitante não pode ser nulo ou vazio");
        }

        String nomeTrimmed = nomeSolicitante.trim();

        if (nomeTrimmed.length() < 3) {
            throw new DomainException("Nome do solicitante deve ter pelo menos 3 caracteres");
        }

        if (!nomeTrimmed.matches("^[\\p{L} ]+$")) {
            throw new DomainException("Nome do solicitante deve conter apenas letras e espaços");
        }

        return nomeTrimmed;
    }

    private String validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new DomainException("Descrição não pode ser nula ou vazia");
        }

        String descricaoTrimmed = descricao.trim();

        if (descricaoTrimmed.length() < 10) {
            throw new DomainException("Descrição deve ter pelo menos 10 caracteres");
        }

        if (descricaoTrimmed.length() > 500) {
            throw new DomainException("Descrição deve ter no máximo 500 caracteres");
        }

        return descricaoTrimmed;
    }

    private Endereco validarEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new DomainException("Endereço não pode ser nulo");
        }
        return endereco;
    }

    private CpfCnpj validarCpfCnpj(CpfCnpj cpfCnpj) {
        if (cpfCnpj == null) {
            throw new DomainException("CPF/CNPJ é obrigátorio");
        }

        return cpfCnpj;
    }

    private Contato validarContato(Contato contato) {
        if (contato == null) {
            throw new DomainException("Contato não pode ser nulo");
        }
        return contato;
    }

}