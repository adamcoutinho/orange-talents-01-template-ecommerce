package br.com.zup.mercadolivre.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface EmailBox {
    public void send(@NotBlank @Email String from , String to, String subject, String composeEmail);
}
