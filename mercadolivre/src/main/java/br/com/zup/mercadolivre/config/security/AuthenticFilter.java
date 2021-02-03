package br.com.zup.mercadolivre.config.security;

import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class AuthenticFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioDetailService usuarioDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try {
            String tokenJwtRequest = parseJwtToken(request);

            if (!tokenJwtRequest.isEmpty() && jwtService.validateJwtToken(tokenJwtRequest)) {

                String username = jwtService.getUserNameFromJwtToken(tokenJwtRequest);

                Usuario usuario = (Usuario) usuarioDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, Collections.EMPTY_LIST);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {

        }


        filterChain.doFilter(request, response);
    }

    private String parseJwtToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (!token.isEmpty() || token.startsWith("Beader")) {
            return token.substring(7, token.length());
        }

        return null;
    }
}
