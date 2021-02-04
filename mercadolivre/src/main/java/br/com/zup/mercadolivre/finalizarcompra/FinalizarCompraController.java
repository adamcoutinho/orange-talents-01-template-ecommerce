package br.com.zup.mercadolivre.finalizarcompra;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/finalizarcompra")
public class FinalizarCompraController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/")
    public ResponseEntity<String> registrarCompra(@RequestBody @Valid FinalizarCompraFormRequest request) {







        Compra compra = new Compra();





        return ResponseEntity.ok().body("");
    }
}
