package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaFormRequest {

    @NotBlank(message = "informe o nome da categoria.")
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "categoria já existe.")
    private String nome;

    private Long idCategoria;

    public CategoriaFormRequest(@NotBlank(message = "informe o nome da categoria.") String nome) {
        this.nome = nome;
    }


    public CategoriaFormRequest(@NotBlank(message = "informe o nome da categoria.") String nome, Long idCategoria) {
        this.nome = nome;
        this.idCategoria = idCategoria;
    }

    public Optional<Categoria> toCategoriaMae(EntityManager manager) {
        if (Optional.ofNullable(this.idCategoria).isEmpty()) return Optional.ofNullable(null);
        return Optional.ofNullable(manager.find(Categoria.class, this.idCategoria));
    }


    public Categoria toModel(EntityManager manager) {

        Optional<Categoria> categoria = toCategoriaMae(manager);

        if (categoria.isPresent()) {
            return new Categoria(this.nome, categoria.get());
        }

        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }
}
