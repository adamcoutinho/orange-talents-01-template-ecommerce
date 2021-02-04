package br.com.zup.mercadolivre.email;

import br.com.zup.mercadolivre.facaumapergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class EmailService {

    @Autowired
    private EmailBox emailBox;

    public void enviarEmailNovaPerguntaVendedor(@Valid  Pergunta pergunta){

        emailBox.send(pergunta.getUsuario().getLogin(),"oragentTalent@gmail.com", "Nova Pergunta","<html>"+pergunta.getTitulo()+"</html>");
    }
}
