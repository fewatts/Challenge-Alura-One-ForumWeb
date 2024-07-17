package com.api.forumweb.app.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Serviço responsável pela geração e validação de tokens JWT.
 */
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT para o usuário fornecido.
     *
     * @param usuario O usuário para quem o token será gerado.
     * @return O token JWT gerado.
     * @throws RuntimeException Se ocorrer um erro durante a criação do token.
     */
    public String gerarToken(com.api.forumweb.app.domain.model.Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("FORUM WEB ARTS")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    /**
     * Obtém o assunto (subject) do token JWT fornecido.
     *
     * @param tokenJWT O token JWT a ser verificado.
     * @return O assunto (subject) do token JWT.
     * @throws RuntimeException Se o token JWT for inválido ou expirado.
     */
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("FORUM WEB ARTS")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    /**
     * Calcula a data de expiração do token JWT (2 horas a partir do momento atual).
     *
     * @return A data de expiração do token como um objeto Instant.
     */
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
