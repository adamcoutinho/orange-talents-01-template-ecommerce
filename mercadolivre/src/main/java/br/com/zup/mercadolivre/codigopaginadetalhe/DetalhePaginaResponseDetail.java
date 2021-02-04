package br.com.zup.mercadolivre.codigopaginadetalhe;

import br.com.zup.mercadolivre.cadastroproduto.Produto;

import java.util.List;
import java.util.Map;

public class DetalhePaginaResponseDetail {


    private List<String> links;

    private String produto;

    private Map<String, String> caracteristicas;

    private Double media;

    private Long numeroTotalNotas;

    private List<String> opinioes;

    private List<String> perguntas;


    public DetalhePaginaResponseDetail(Produto produto) {
        this.links = produto.obterLinks();
        this.produto = produto.getNome();
        this.numeroTotalNotas = produto.obterNumeroTotalNotas();
        this.media = produto.obterMediaNota();
        this.caracteristicas = produto.obterMapaCaracteristicas();
        this.perguntas = produto.obterPerguntasDescricao();
        this.opinioes = produto.obterOpinioes();
    }

    public List<String> getLinks() {
        return links;
    }

    public String getProduto() {
        return produto;
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public Double getMedia() {
        return media;
    }

    public Long getNumeroTotalNotas() {
        return numeroTotalNotas;
    }

    public List<String> getOpinioes() {
        return opinioes;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }
}
