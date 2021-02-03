package br.com.zup.mercadolivre.email;

import br.com.zup.mercadolivre.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailBox emailBox;

    public void enviarEmailPergunta(Pergunta pergunta){

    }
}
