package br.com.zup.mercadolivre.finalizarcompra;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.cadastrousuario.Usuario;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_compra")
    @SequenceGenerator(name = "sequence_compra", sequenceName = "sq_compra", allocationSize = 1)
    private Long id;
    @NotNull
    private StatusCompra status;

    @ManyToOne
    @NotNull
    private Produto produto;
    @NotNull
    private Integer quantidade;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;


    @Deprecated
    public Compra() {
    }

    public StatusCompra getStatus() {
        return status;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
