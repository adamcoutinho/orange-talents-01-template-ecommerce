package br.com.zup.mercadolivre.adicionaopiniao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/opiniao")
public class OpiniaoController {

    @Autowired
    private EntityManager manager;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid OpiniaoFormRequest request) {

        Opiniao opiniao = request.toModel(manager);
        manager.persist(opiniao);
        return ResponseEntity.ok().body(opiniao);
    }


}
