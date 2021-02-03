package br.com.zup.mercadolivre.categoria;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_usuario")
    @SequenceGenerator(name = "sequence_usuario", sequenceName = "sq_usuario", allocationSize = 1)
    private Long id;

    @NotBlank(message = "informe o nome da categoria")
    @Column(unique = true)
    private String nome;

    @ManyToOne
    private Categoria categoria;

    @Deprecated
    public Categoria() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Categoria(@NotBlank(message = "informe o nome da categoria") String nome) {
        this.nome = nome;
    }


    public Categoria(@NotBlank(message = "informe o nome da categoria") String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)


    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Long getId() {
        return id;
    }


}
