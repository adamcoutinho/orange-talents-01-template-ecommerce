package br.com.zup.mercadolivre.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class ConfigureSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioDetailService usuarioDetailService;

    @Bean
    public AuthenticFilter authenticFilter(){
        return new AuthenticFilter();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return  new CustomAutenticationEntryPoint();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.usuarioDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/usuario/cadastrar").permitAll()
                .antMatchers("/categoria/cadastrar").permitAll()
                 .antMatchers("/produto/cadastrar").permitAll()
                .antMatchers("/produto/cadastrar").permitAll()
                .antMatchers("/produto/{id}/imagens").permitAll()
                .antMatchers("/opiniao/cadastrar").permitAll()
                .antMatchers("/pergunta/cadastrar").permitAll()
                .antMatchers("/codigopaginadetalhe/produto/{id}").permitAll()
                .antMatchers("/finalizarcompra").permitAll()
                .antMatchers("/autenticar").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .addFilterBefore(this.authenticFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());

    }

}
