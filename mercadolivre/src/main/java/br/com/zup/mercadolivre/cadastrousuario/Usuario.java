package br.com.zup.mercadolivre.cadastrousuario;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_usuario")
    @SequenceGenerator(name = "sequence_usuario",sequenceName = "sq_usuario" ,allocationSize = 1)
    private Long id;

    @NotNull(message = "não pode ser nulo.")
    @NotBlank(message = "informe um email válido.")
    @Email(message = "email não possui formato valido.")
    @Column(unique = true)
    private String login;

    @NotNull
    @NotBlank
    @Size(min = 6)
    private String senha;

    @NotNull
    private LocalDateTime instant = LocalDateTime.now();
    @ManyToMany
    private List<Regra> regras =new ArrayList<>();


    @Deprecated
    public Usuario() {
    }
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Usuario(@NotNull @NotBlank @Email String login, @NotNull @NotBlank @Min(6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.regras;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Boolean logado(){
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(instant, usuario.instant) && Objects.equals(regras, usuario.regras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, senha, instant, regras);
    }
}
