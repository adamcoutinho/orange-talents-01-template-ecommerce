package br.com.zup.mercadolivre.finalizarcompra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {
    PAYPAL {
        @Override
        String obterUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
             String paypalUrl = uriComponentsBuilder.path("/retorno/paypal").buildAndExpand(compra.getId()).toString();
            return "http://paypal/" + compra.getId() + "?redirectUrl="+paypalUrl;
        }
    }, PAGUE_SEGURO {
        @Override
        String obterUrlRetorno(Compra compra,UriComponentsBuilder uriComponentsBuilder) {
            String pagueSeguroUrl = uriComponentsBuilder.path("/retorno/pagueseguro").buildAndExpand(compra.getId()).toString();
            return "http://pagueseguro.com/returnId=" + compra.getId() + "&redirectUrl="+pagueSeguroUrl;
        }

    };

    abstract String obterUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);




}
