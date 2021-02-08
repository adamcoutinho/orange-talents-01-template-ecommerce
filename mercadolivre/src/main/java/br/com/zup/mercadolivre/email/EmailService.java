package br.com.zup.mercadolivre.email;

import br.com.zup.mercadolivre.facaumapergunta.Pergunta;
import br.com.zup.mercadolivre.finalizarcompra.Compra;
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

    public void enviarEmailNovaCompra(Compra novaCompra) {
        emailBox.send(novaCompra.getUsuario().getLogin(),"oragentTalent@gmail.com", "Nova Compra","<html>"+"Código da compra: "+novaCompra.getId()+"</html>");
    }
}
