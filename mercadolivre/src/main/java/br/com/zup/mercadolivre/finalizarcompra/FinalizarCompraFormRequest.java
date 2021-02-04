package br.com.zup.mercadolivre.finalizarcompra;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.cadastrousuario.Usuario;
import br.com.zup.mercadolivre.validation.ExistId;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class FinalizarCompraFormRequest {

    private String usuario;

    @ExistId(domainClass = Produto.class,fieldName = "id",message = "produto não existe.")
    private Long idProduto;

    @NotNull(message = "informe uma quantidade.")
    @Positive(message = "quantidade não pode ser negativa.")
    private Integer quantidade;

    private GatewayPagamento gatewayPagamento;


    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Produto toProduto(EntityManager manager){
        return manager.find(Produto.class,this.idProduto);
    }

    public Usuario toUsuario(EntityManager manager) {
        return (Usuario) manager.createQuery("SELECT u FROM Usuario u WHERE u.login like :usuario")
                .setParameter("usuario", this.usuario).getSingleResult();
    }
    public Compra toModel(EntityManager manager){

        Usuario usuario = toUsuario(manager);

        Produto produto = toProduto(manager);

        return new Compra();

    }

    public String getUsuario() {
        return usuario;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
