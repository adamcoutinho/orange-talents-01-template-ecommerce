package br.com.zup.mercadolivre.codigopaginadetalhe;

import br.com.zup.mercadolivre.cadastroproduto.Produto;
import br.com.zup.mercadolivre.validation.ExistId;
import br.com.zup.mercadolivre.validation.UniqueValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.Objects;

@RestController
@RequestMapping("/codigopaginadetalhe")
public class DetalhePaginaController {

    @Autowired
    private EntityManager manager;

    @GetMapping("/produto/{id}")
    public ResponseEntity<?> buscarDetalhe(@PathVariable("id") Long id) {

        if(Objects.isNull(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id do produto não pode ser nulo.");
        }

        Produto produto = this.manager.find(Produto.class, id);

        DetalhePaginaResponseDetail responseDatail = new DetalhePaginaResponseDetail(produto);

        return  ResponseEntity.ok(responseDatail);
    }
}
