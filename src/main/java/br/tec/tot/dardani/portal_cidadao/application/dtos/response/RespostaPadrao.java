package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RespostaPadrao {

    private final Integer codigo;
    private final String mensagem;
    private final String status;
    private final LocalDateTime dataHora;

    public static RespostaPadrao recursoCriado() {
        return new RespostaPadrao(HttpStatus.CREATED.value(), "Operação realizada com sucesso",
                HttpStatus.CREATED.name(), LocalDateTime.now());
    }

}
