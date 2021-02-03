package br.com.zup.mercadolivre.usuario;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "regra")
public class Regra implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_regra")
    @SequenceGenerator(name = "sequence_regra",sequenceName = "sq_regra" ,allocationSize = 1)
    private Long id;

    private String nome;

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
