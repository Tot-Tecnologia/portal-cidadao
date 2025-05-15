package br.tec.tot.dardani.portal_cidadao.domain.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public final class Protocolo {

    private final Long id;

    private final CpfCnpj cpfCnpj;
    private final Contato contato;
    private final Endereco endereco;

    private final String descricao;
    private final String nomeSolicitante;
    private final String numeroProtocolo;
    private final ProtocoloStatusEnum status;
    private final Long tipoDocumento;

    private List<Documento> documentos;
    private List<Guia> guias;

    private Usuario usuario;

    public Protocolo(CpfCnpj cpfCnpj, String nomeSolicitante, String descricao,
            Endereco endereco, Contato contato, Long tipoDocumento) {

        this(null, cpfCnpj, nomeSolicitante, descricao, gerarProtocolo(), endereco, contato,
                ProtocoloStatusEnum.EM_ANALISE, tipoDocumento);

    }

    public Protocolo(
            Long id,
            CpfCnpj cpfCnpj,
            String nomeSolicitante,
            String descricao,
            String numeroProtocolo,
            Endereco endereco,
            Contato contato,
            ProtocoloStatusEnum status,
            Long tipoDocumento) {
        this.id = id;
        this.numeroProtocolo = numeroProtocolo;
        this.nomeSolicitante = validarNomeSolicitante(nomeSolicitante);
        this.endereco = validarEndereco(endereco);
        this.contato = validarContato(contato);
        this.cpfCnpj = validarCpfCnpj(cpfCnpj);
        this.tipoDocumento = tipoDocumento;
        this.descricao = descricao;
        this.status = status;
    }

    public void atribuirProprietario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<Guia> getGuias() {
        if (this.guias == null) {
            return List.of();
        }
        return this.guias;
    }

    public void adicionarDocumento(Documento documento) {
        if (this.documentos == null) {
            this.documentos = new ArrayList<>();
        }
        this.documentos.add(documento);
    }

    public List<Documento> getDocumentos() {
        if (this.documentos == null) {
            return List.of();
        }
        return this.documentos;
    }

    public void adicionarArquivo(Guia guia) {
        if (this.guias == null) {
            this.guias = new ArrayList<>();
        }
        this.guias.add(guia);
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