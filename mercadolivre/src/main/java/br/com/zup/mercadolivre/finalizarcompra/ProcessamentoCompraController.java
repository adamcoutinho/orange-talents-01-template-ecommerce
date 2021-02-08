package br.com.zup.mercadolivre.finalizarcompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/processacompra")
public class ProcessamentoCompraController {


    @Autowired
    private EntityManager manager;

    @PostMapping("/paypal/{id}")
    @Transactional
    public ResponseEntity<String> paypalProcessamento(@PathVariable("id") Long idCompra, PaypalFormRequest request) {


        return ResponseEntity.ok("ok");
    }

    @PostMapping("/pague-seguro/{id}")
    @Transactional
    public ResponseEntity<String> pagueSeguroProcessamento(@PathVariable("id") Long idCompra , PagueSeguroFormRequest request) {
        return ResponseEntity.ok("ok");
    }




}
