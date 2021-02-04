package br.com.zup.mercadolivre.facaumapergunta;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.cadastrousuario.Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "pergunta")
public class Pergunta implements Comparable<Pergunta> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_pergunta")
    @SequenceGenerator(name = "sequence_pergunta", sequenceName = "sq_pergunta", allocationSize = 1)
    private Long id;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotNull(message = "id do usuário não pode ser nulo.") Usuario usuario, @NotNull(message = "id do produto não pode ser nulo.") Produto produto, @NotBlank(message = "informe a pergunta.") String titulo) {
        this.usuario = usuario;
        this.produto = produto;
        this.titulo = titulo;
    }

    @ManyToOne
    @NotNull(message = "id do usuário não pode ser nulo.")
    private Usuario usuario;

    private LocalDate instant = LocalDate.now();

    @ManyToOne
    @NotNull(message = "id do produto não pode ser nulo.")
    private Produto produto;

    @NotBlank(message = "informe a pergunta.")
    private String titulo;

    @Override
    public int compareTo(Pergunta pergunta) {
        return id.compareTo(pergunta.id);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getTitulo() {
        return titulo;
    }


}
