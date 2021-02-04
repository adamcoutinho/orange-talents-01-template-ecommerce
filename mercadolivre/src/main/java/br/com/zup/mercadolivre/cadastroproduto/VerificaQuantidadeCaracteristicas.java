package br.com.zup.mercadolivre.cadastroproduto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VerificaQuantidadeCaracteristicas implements Validator {


    @Override
    public boolean supports(Class<?> kclass) {
        if (ProdutoFormRequest.class.equals(kclass)) return true;
        if (ImagemFormRequest.class.equals(kclass)) return true;

        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        ProdutoFormRequest produtoFormRequest = (ProdutoFormRequest) target;

        if (produtoFormRequest.getCaracteristicas().size() < 3) {
            errors.rejectValue("caracteristicas", null, "deve pelo menos ter 3 características.");
        }

    }
}
