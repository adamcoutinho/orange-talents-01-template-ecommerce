package br.com.zup.mercadolivre.facaumapergunta;

import br.com.zup.mercadolivre.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private EmailService emailService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PerguntaFormRequest request) {

        Pergunta novaPergunta = request.toModel(this.manager);

        this.emailService.enviarEmailNovaPerguntaVendedor(novaPergunta);

        this.manager.persist(novaPergunta);

        List<Pergunta>  perguntas  = this.manager.createQuery("SELECT p FROM Pergunta p",Pergunta.class).getResultList();

        perguntas.add(novaPergunta);
        return ResponseEntity.ok(perguntas);
    }

}
