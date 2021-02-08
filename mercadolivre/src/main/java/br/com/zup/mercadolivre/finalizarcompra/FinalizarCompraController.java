package br.com.zup.mercadolivre.finalizarcompra;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/finalizarcompra")
public class FinalizarCompraController {


    @Autowired
    private EntityManager manager;

    @Autowired
    private EmailService emailService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> registrarCompra(@RequestBody @Valid FinalizarCompraFormRequest request ,UriComponentsBuilder uriComponentsBuilder) throws BindException {

        Compra novaCompra = request.toModel(manager);

        if(novaCompra.getProduto().substraiEstoque(novaCompra.getQuantidade())){

            manager.persist(novaCompra);
            this.emailService.enviarEmailNovaCompra(novaCompra);
            return ResponseEntity.ok().body(novaCompra.redirercionaUrl(uriComponentsBuilder));
        }

        BindException problemaComEstoque = new BindException(request,"request");
        problemaComEstoque.reject(null,"Não foi possível realizar a compra por conta do estoque");

        throw problemaComEstoque;
    }
}
