package br.com.zup.mercadolivre.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "caracteristica")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_caracteristica")
    @SequenceGenerator(name = "sequence_caracteristica", sequenceName = "sq_caracteristica", allocationSize = 1)
    private Long id;

    @NotBlank(message = "informe um campo")
    private String nome;
    @NotBlank(message = "informe uma descrição")
    private String descricao;


    @ManyToOne
    @NotNull(message = "produto não pode ser nulo.")
    private Produto produto;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Deprecated
    public Caracteristica() {
    }

    public Caracteristica(@NotBlank(message = "informe um campo") String nome, @NotBlank(message = "informe uma descrição") String descricao, @NotNull(message = "produto não pode ser nulo.") Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }


}
