package br.com.zup.mercadolivre.email;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Component
@Qualifier("vendedor")
public class EmailVendedor implements EmailBox{

    @Override
    public void send(@NotBlank @Email String from, String to, String subject, String composeEmail) {
        System.out.println(new ObjectEmail(from,to,subject,composeEmail));
    }
}
