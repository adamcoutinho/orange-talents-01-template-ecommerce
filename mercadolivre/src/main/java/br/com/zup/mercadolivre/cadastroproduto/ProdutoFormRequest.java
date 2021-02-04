package br.com.zup.mercadolivre.cadastroproduto;

import br.com.zup.mercadolivre.cadastrocategoria.Categoria;
import br.com.zup.mercadolivre.cadastrousuario.Usuario;
import br.com.zup.mercadolivre.validation.ExistId;
import br.com.zup.mercadolivre.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProdutoFormRequest {

    @NotBlank(message = "usuário deslogado.")
    private String usuario;

    @NotBlank(message = "informe o nome do produto.")
    @UniqueValue(domainClass = Produto.class, fieldName = "nome", message = "produto já existe.")
    private String nome;

    @Positive(message = "valor deve ser maior que 0.")
    private BigDecimal valor;

    @Min(value = 0, message = "quantidade tem que ser maior ou igual a zero.")
    private Integer quantidade;

    private List<CaracteristicaFormRequest> caracteristicas;

    @NotBlank(message = "informe uma descrição.")
    @Size(max = 1000, message = "descricção só pode ter no máximo 1000 caracteres.")
    private String descricao;

    @NotNull(message = "id categoria não pode ser nula.")
    @ExistId(domainClass = Categoria.class, fieldName = "id", message = "categoria não existe.")
    private Long idCategoria;

    public Optional<Categoria> toCategoria(EntityManager manager) {
        if (Optional.ofNullable(this.idCategoria).isEmpty()) return Optional.ofNullable(null);
        return Optional.ofNullable(manager.find(Categoria.class, this.idCategoria));
    }

    public Usuario toUsuario(EntityManager manager) {
        return (Usuario) manager.createQuery("SELECT u FROM Usuario u WHERE u.login like :usuario")
                .setParameter("usuario", this.usuario).getSingleResult();
    }


    public Produto toModel(EntityManager manager) {

        Optional<Categoria> categoria = toCategoria(manager);

        Usuario usuario = toUsuario(manager);

        return new Produto(this.nome, this.valor, this.quantidade, getCaracteristicas(), this.descricao, categoria.get(), usuario);
    }


    @Override
    public String toString() {
        return "ProdutoFormRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<CaracteristicaFormRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getUsuario() {
        return usuario;
    }
}
