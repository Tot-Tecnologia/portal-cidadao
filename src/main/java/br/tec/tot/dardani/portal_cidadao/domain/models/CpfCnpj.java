package br.tec.tot.dardani.portal_cidadao.domain.models;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public final class CpfCnpj {
    private final String valor;

    public CpfCnpj(String documento) {
        this.valor = validar(documento);
    }

    public Boolean isCnpj() {
        return this.valor.length() > 11;
    }

    private String validar(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new DomainException("CPF/CNPJ não pode ser nulo ou vazio");
        }

        String documentoLimpo = documento.trim().replaceAll("[^0-9]", "");

        if (documentoLimpo.length() == 11) {
            validarCPF(documentoLimpo);
        } else if (documentoLimpo.length() == 14) {
            validarCNPJ(documentoLimpo);
        } else {
            throw new DomainException("CPF/CNPJ deve ter 11 (CPF) ou 14 (CNPJ) dígitos");
        }

        return documentoLimpo;
    }

    private void validarCPF(String cpf) {
        if (cpf.matches("(\\d)\\1{10}")) {
            throw new DomainException("CPF inválido");
        }

        int[] pesosPrimeiroDigito = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] pesosSegundoDigito = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

        String digitos = cpf.substring(0, 9);
        String digitosVerificadores = cpf.substring(9);

        int primeiroDigito = calcularDigitoVerificador(digitos, pesosPrimeiroDigito);
        int segundoDigito = calcularDigitoVerificador(digitos + primeiroDigito, pesosSegundoDigito);

        if (!digitosVerificadores.equals(String.valueOf(primeiroDigito) + segundoDigito)) {
            throw new DomainException("CPF inválido");
        }
    }

    private void validarCNPJ(String cnpj) {
        if (cnpj.matches("(\\d)\\1{13}")) {
            throw new DomainException("CNPJ inválido");
        }

        int[] pesosPrimeiroDigito = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] pesosSegundoDigito = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

        String digitos = cnpj.substring(0, 12);
        String digitosVerificadores = cnpj.substring(12);

        int primeiroDigito = calcularDigitoVerificador(digitos, pesosPrimeiroDigito);
        int segundoDigito = calcularDigitoVerificador(digitos + primeiroDigito, pesosSegundoDigito);

        if (!digitosVerificadores.equals(String.valueOf(primeiroDigito) + segundoDigito)) {
            throw new DomainException("CNPJ inválido");
        }
    }

    private int calcularDigitoVerificador(String numeros, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < numeros.length(); i++) {
            soma += Character.getNumericValue(numeros.charAt(i)) * pesos[i];
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CpfCnpj cpfCnpj = (CpfCnpj) o;
        return valor.equals(cpfCnpj.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }

    @Override
    public String toString() {
        return valor;
    }
}