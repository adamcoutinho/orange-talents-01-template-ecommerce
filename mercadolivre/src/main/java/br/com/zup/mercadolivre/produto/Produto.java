package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.opiniao.Opiniao;
import br.com.zup.mercadolivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_produto")
    @SequenceGenerator(name = "sequence_produto", sequenceName = "sq_produto", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @NotBlank(message = "informe o nome do produto.")
    private String nome;

    @Positive(message = "valor deve ser maior que 0.")
    private BigDecimal valor;
    @Min(value = 0,message = "quantidade tem que ser maior ou igual a zero.")
    private Integer quantidade;

    @NotNull(message = "caracteristicas não pode ser nulas")
    @Size(max = 3)
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Caracteristica> caracteristicas;

    @NotBlank(message = "informe uma descrição.")
    @Size(max = 1000,message = "descricção só pode ter no máximo 1000 caracteres.")
    private String descricao;

    @NotNull(message = "categoria não pode ser nula.")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Categoria categoria;

    private LocalDateTime instant = LocalDateTime.now();

    @OneToMany(mappedBy = "produto",cascade = CascadeType.MERGE)
    private List<Imagem> imagens;

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private List<Opiniao> opinioes;

    @Deprecated
    public Produto() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Produto(@NotBlank(message = "informe o nome do produto.") String nome, @Positive BigDecimal valor, @Min(value = 0, message = "quantidade tem que ser maior ou igual a zero.") Integer quantidade, @Size(max = 3) List<CaracteristicaFormRequest> caracteristicas, @NotBlank(message = "informe uma descrição.") @Size(max = 1000, message = "descricção só pode ter no máximo 1000 caracteres.") String descricao, @NotNull(message = "categoria não pode ser nula.") Categoria categoria,Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas.stream().map(caracteristicaFormRequest -> caracteristicaFormRequest.toModel(this)).collect(Collectors.toList());
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario= usuario;
    }

    public Produto(List<String> imagensUrls) {
        this.imagens = imagens;
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

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public List<Imagem> getImagens() {
        return imagens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(usuario, produto.usuario) && Objects.equals(nome, produto.nome) && Objects.equals(valor, produto.valor) && Objects.equals(quantidade, produto.quantidade) && Objects.equals(caracteristicas, produto.caracteristicas) && Objects.equals(descricao, produto.descricao) && Objects.equals(categoria, produto.categoria) && Objects.equals(instant, produto.instant) && Objects.equals(imagens, produto.imagens) && Objects.equals(opinioes, produto.opinioes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, nome, valor, quantidade, caracteristicas, descricao, categoria, instant, imagens, opinioes);
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public void adicionaImagens(List<String> imagensUrl) {
      this.imagens=imagensUrl.stream().map(url->new Imagem(url,this)).collect(Collectors.toList());
    }
}
