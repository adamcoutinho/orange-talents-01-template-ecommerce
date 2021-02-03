package br.com.zup.mercadolivre.validation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue,Object> {


    @Autowired
    private EntityManager manager;
    private Class<?> kclass;
    private String fieldName;
    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.kclass =constraintAnnotation.domainClass();
        this.fieldName=constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("SELECT 1 FROM "+this.kclass.getName()+" WHERE "+this.fieldName+" =:value");
        query.setParameter("value",value);

        return query.getResultList().isEmpty();
    }
}
