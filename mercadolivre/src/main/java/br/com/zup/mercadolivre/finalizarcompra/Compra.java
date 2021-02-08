package br.com.zup.mercadolivre.finalizarcompra;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.cadastrousuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_compra")
    @SequenceGenerator(name = "sequence_compra", sequenceName = "sq_compra", allocationSize = 1)
    private Long id;

    @ManyToOne
    @NotNull
    private Produto produto;

    @NotNull
    private Integer quantidade;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @Enumerated
    private GatewayPagamento gatewayPagamento;

    @OneToMany(mappedBy = "compra")
    private Set<Transacao> transacoes = new HashSet<>();


    @Deprecated
    public Compra() {
    }


    public Compra(@NotNull Produto produto, @NotNull Integer quantidade, @NotNull Usuario usuario, @NotNull GatewayPagamento gatewayPagamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.gatewayPagamento = gatewayPagamento;
    }

    public Boolean transacoesConcluidasComSucesso(){
        return !getTransacoes().stream().filter(Transacao::sucesso).collect(Collectors.toSet()).isEmpty();
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

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Long getId() {
        return id;
    }

    public Set<Transacao> getTransacoes() {
        return transacoes;
    }


    public String redirercionaUrl(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.obterUrlRetorno(this,uriComponentsBuilder) ;
    }

    public boolean apenasUmaTransacao(){
        return !getTransacoes()
                .stream()
                .filter(Transacao::sucesso)
                .collect(Collectors.toSet())
                .isEmpty() &&
                (getTransacoes()
                        .stream()
                        .filter(Transacao::sucesso)
                        .collect(Collectors.toSet()).size() < 1);

    }
}
