package br.com.zup.mercadolivre.adicionaopiniao;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.cadastrousuario.Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "opiniao")
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_opiniao")
    @SequenceGenerator(name = "sequence_opiniao", sequenceName = "sq_opiniao", allocationSize = 1)
    private Long id;

    @Min(value = 1, message = "mínimo da note é 1.")
    @Max(value = 5, message = "máximo da nota é 5.")
    private Integer nota;

    @NotBlank(message = "informe um título.")
    private String titulo;

    @Size(max = 500, message = "máximo de caracteres é 500.")
    @NotBlank(message = "informe uma descrição.")
    private String descricao;

    @NotNull(message = "id do usuário não pode ser nulo.")
    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    @NotNull(message = "id do produto não pode ser nulo.")
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Min(value = 1, message = "mínimo da note é 1.") @Max(value = 5, message = "máximo da nota é 5.") Integer nota, @NotBlank(message = "informe um título.") String titulo, @Size(max = 500, message = "máximo de caracteres é 500.") @NotBlank(message = "informe uma descrição.") String descricao, @NotNull(message = "id do usuário não pode ser nulo.") Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
