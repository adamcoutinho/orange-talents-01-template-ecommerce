package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "pergunta")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_pergunta")
    @SequenceGenerator(name = "sequence_pergunta", sequenceName = "sq_pergunta", allocationSize = 1)
    private Long id;

    @Deprecated
    public Pergunta() {
    }

    @ManyToOne
    @NotNull(message = "id do usuário não pode ser nulo.")
    private Usuario usuario;

    private LocalDate instant  = LocalDate.now();

    @ManyToOne
    @NotNull(message = "id do produto não pode ser nulo.")
    private Produto produto;





}
