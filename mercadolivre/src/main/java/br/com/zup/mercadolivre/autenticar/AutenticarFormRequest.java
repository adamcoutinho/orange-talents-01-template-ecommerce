package br.com.zup.mercadolivre.autenticar;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutenticarFormRequest {
    @Email(message = "email não está no formato valido.")
    @NotBlank(message = "informe um email")
    private String login;
    @Size(min = 6, message = "senha pode ter no mínimo 6 carateres.")
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
