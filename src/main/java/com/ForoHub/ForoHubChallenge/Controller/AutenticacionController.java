package com.ForoHub.ForoHubChallenge.Controller;

import com.ForoHub.ForoHubChallenge.Base.user.DatosAutenticarUser;
import com.ForoHub.ForoHubChallenge.Base.user.Usuario;
import com.ForoHub.ForoHubChallenge.Infra.TokenService;
import com.ForoHub.ForoHubChallenge.Infra.DatosJWTTOKEN;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity hacerLogin (@RequestBody @Valid DatosAutenticarUser datosAutenticarUser) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutenticarUser.login(),
                datosAutenticarUser.password());

        var autenticarUser = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generarToken((Usuario) autenticarUser.getPrincipal());
        return ResponseEntity.ok(new DatosJWTTOKEN(JWTToken));

    }

}
