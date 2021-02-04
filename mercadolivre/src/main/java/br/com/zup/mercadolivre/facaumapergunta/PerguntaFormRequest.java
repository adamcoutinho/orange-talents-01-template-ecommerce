package br.com.zup.mercadolivre.facaumapergunta;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.cadastrousuario.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaFormRequest {
    @NotBlank(message = "usuário deslogado.")
    private String usuario;

    @NotNull(message = "id do produto não pode ser nulo.")
    private Long idProduto;

    @NotBlank(message = "informe uma pergunta.")
    private String titulo;

    public Usuario toUsuario(EntityManager manager) {
        return (Usuario) manager.createQuery("SELECT u FROM Usuario u WHERE u.login like :usuario")
                .setParameter("usuario", this.usuario).getSingleResult();
    }

    public Produto toProduto(EntityManager manager) {
        return manager.find(Produto.class, this.idProduto);
    }

    public Pergunta toModel(EntityManager manager) {
        Usuario usuario = toUsuario(manager);
        Produto produto = toProduto(manager);

        return new Pergunta(usuario, produto, titulo);
    }

    public String getUsuario() {
        return usuario;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getTitulo() {
        return titulo;
    }
}
