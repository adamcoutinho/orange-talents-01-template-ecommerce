package br.com.zup.mercadolivre.autenticar;

import br.com.zup.mercadolivre.config.security.JwtService;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AutenticarController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@RequestBody @Valid AutenticarFormRequest request) {

        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String tokenGenerated = jwtService.createTokent(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Map<String, String> usuarioMap = new HashMap<>();
        usuarioMap.put("usuário", usuario.getLogin());
        usuarioMap.put("token", tokenGenerated);


        return ResponseEntity.status(HttpStatus.OK).body(usuarioMap);
    }

}
