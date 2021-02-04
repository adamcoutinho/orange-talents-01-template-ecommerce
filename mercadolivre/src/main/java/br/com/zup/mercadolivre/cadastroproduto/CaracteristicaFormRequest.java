package br.com.zup.mercadolivre.cadastroproduto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaFormRequest {


    @NotBlank(message = "informe um campo")
    private String nome;
    @NotBlank(message = "informe uma descrição")
    private String descricao;

    public CaracteristicaFormRequest(@NotBlank(message = "informe um campo") String nome, @NotBlank(message = "informe uma descrição") String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica toModel(Produto produto) {
        return new Caracteristica(this.nome, this.descricao,produto);
    }
}
