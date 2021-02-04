package br.com.zup.mercadolivre.cadastrousuario;

import br.com.zup.mercadolivre.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioFormRequest {

    @Email(message = "informe um email valido.")
    @NotBlank(message = "informe um email.")
    @NotNull(message = "email não pode nulo.")
    @UniqueValue(domainClass = Usuario.class,fieldName = "login",message = "email já existe.")
    private String login;

    @NotBlank(message = "informe uma senha")
    @NotNull(message = "senha não pode nula.")
    @Size(min = 6,message = "senha tem que ter no mínimo 6 caracters")
    private String senha;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UsuarioFormRequest(@Email @NotBlank @NotNull String login, @NotBlank @NotNull @Min(6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login,new BCryptPasswordEncoder().encode(this.senha));
    }
}
