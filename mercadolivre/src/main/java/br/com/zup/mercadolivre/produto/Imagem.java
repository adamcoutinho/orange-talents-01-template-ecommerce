package br.com.zup.mercadolivre.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "imagem")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_caracteristica")
    @SequenceGenerator(name = "sequence_caracteristica", sequenceName = "sq_caracteristica", allocationSize = 1)
    private Long id;

    @NotBlank
    private String link;

    @ManyToOne
    @NotNull
    @JsonIgnore
    private Produto produto;

    @Deprecated
    public Imagem() {
    }

    public Imagem(@NotBlank String link, @NotNull Produto produto) {
        this.link = link;
        this.produto = produto;
    }

    public String getLink() {
        return link;
    }

    public Produto getProduto() {
        return produto;
    }
}
