package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

  @Autowired
  private EmailService emailService;

 @PostMapping("/cadastrar")
 public ResponseEntity<?> cadastrar(@RequestBody @Valid PerguntaFormRequest request) {

     Pergunta pergunta = request.toModel();


     return ResponseEntity.ok(null);
 }

}
