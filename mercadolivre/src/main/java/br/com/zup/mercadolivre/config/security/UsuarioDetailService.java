package br.com.zup.mercadolivre.config.security;

import br.com.zup.mercadolivre.cadastrousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;
@Service
public class UsuarioDetailService implements UserDetailsService {

    @Autowired
    private EntityManager manager;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Query query =  this.manager.createQuery("SELECT u FROM Usuario u where u.login like :login");

        query.setParameter("login",login);

        Optional<Usuario> usuario = Optional.ofNullable((Usuario) query.getSingleResult());

        if(usuario.isPresent()) return usuario.get();


        throw new UsernameNotFoundException("Usuário não encontrado.");

    }
}
