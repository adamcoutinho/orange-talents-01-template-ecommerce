package br.com.zup.mercadolivre.finalizarcompra;

public enum GatewayPagamento {
    PAYPAL {
        @Override
        String obterUrlRetorno(String identificador,Long idProduto) {
            return "http://paypal/" + identificador + "?redirectUrl="+idProduto;
        }
    }, PAGUE_SEGURO {
        @Override
        String obterUrlRetorno(String identificador,Long idProduto) {
            return "http://pagueseguro.com/returnId=" + identificador + "&redirectUrl="+idProduto;
        }

    };

    abstract String obterUrlRetorno(String identificador,Long idProduto);

    private Object status;


}
