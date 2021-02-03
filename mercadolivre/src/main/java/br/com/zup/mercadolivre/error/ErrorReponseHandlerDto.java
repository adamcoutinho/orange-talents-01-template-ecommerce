package br.com.zup.mercadolivre.error;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorReponseHandlerDto {

    private String instant = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:MM:ss").format(LocalDateTime.now());
    private String campo;

    private String mensagem;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ErrorReponseHandlerDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getInstant() {
        return instant;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
