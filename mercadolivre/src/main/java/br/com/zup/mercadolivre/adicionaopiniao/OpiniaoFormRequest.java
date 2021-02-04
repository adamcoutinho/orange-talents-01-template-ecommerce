package br.com.zup.mercadolivre.adicionaopiniao;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.cadastrousuario.Usuario;
import br.com.zup.mercadolivre.validation.ExistId;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OpiniaoFormRequest {

    @Min(value = 1,message = "mínimo da note é 1.") @Max(value = 5,message = "máximo da nota é 5.")
    private Integer nota;

    @NotBlank(message = "informe um título.")
    @Size(max = 500,message = "máximo de caracteres é 500.")
    private String titulo;

    @Size(max = 500, message = "máximo de caracteres é 500.")
    @NotBlank(message = "informe uma descrição.")
    private String descricao;

    @NotBlank(message = "Usuário deslogado.")
    private String usuario;

    @NotNull(message = "id produto não pode ser nulo.")
    @ExistId(domainClass = Produto.class,fieldName = "id",message = "produto não éxiste.")
    private Long idProduto;

    public Usuario toUsuario(EntityManager manager) {
        return (Usuario) manager.createQuery("SELECT u FROM Usuario u WHERE u.login like :usuario")
                .setParameter("usuario", this.usuario).getSingleResult();
    }

    public Produto toProduto(EntityManager manager){
        return manager.find(Produto.class,this.idProduto);
    }


    public Opiniao toModel(EntityManager manager) {
        Usuario usuario = toUsuario(manager);
        Produto produto = toProduto(manager);
        return new Opiniao(this.nota,this.titulo,this.descricao, usuario,produto);
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

    public String getUsuario() {
        return usuario;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}
