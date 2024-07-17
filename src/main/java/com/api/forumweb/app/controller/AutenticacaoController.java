package com.api.forumweb.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.forumweb.app.domain.dto.dtousuario.DadosAutenticacao;
import com.api.forumweb.app.domain.model.Usuario;
import com.api.forumweb.app.infra.security.DadosTokenJWT;
import com.api.forumweb.app.infra.security.TokenService;

import jakarta.validation.Valid;

/**
 * Controller responsável pela autenticação dos usuários.
 */
@RestController
@RequestMapping("login")
public class AutenticacaoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    /**
     * Endpoint para autenticação de usuários.
     *
     * @param dados Dados de autenticação (email e senha) fornecidos pelo usuário.
     * @return ResponseEntity contendo o token JWT gerado.
     */
    @PostMapping
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacao dados) {
        var autenticacaoToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(autenticacaoToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
