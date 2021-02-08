package br.com.zup.mercadolivre.finalizarcompra;

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
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transacao")
public class Transacao {
    @Deprecated
    public Transacao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_transacao")
    @SequenceGenerator(name = "sequence_transacao", sequenceName = "sq_transacao", allocationSize = 1)
    private Long id;

    private LocalDateTime localDateTime = LocalDateTime.now();

    @NotNull
    private Long idTransacaoPlataforma ;

    @NotNull
    private Long idCompra;
    @Enumerated(EnumType.STRING)
    private StatusTransacao status;

    @ManyToOne
    private Compra compra;


    public Boolean sucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }

    public Long getIdTransacaoPlataforma() {
        return idTransacaoPlataforma;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public Compra getCompra() {
        return compra;
    }


    public Transacao(@NotNull Long idTransacaoPlataforma, @NotNull Long idCompra, StatusTransacao status, Compra compra) {
        this.idTransacaoPlataforma = idTransacaoPlataforma;
        this.idCompra = idCompra;
        this.status = status;
        this.compra = compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id) && Objects.equals(idTransacaoPlataforma, transacao.idTransacaoPlataforma) && Objects.equals(idCompra, transacao.idCompra) && status == transacao.status && Objects.equals(compra, transacao.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idTransacaoPlataforma, idCompra, status, compra);
    }
}
