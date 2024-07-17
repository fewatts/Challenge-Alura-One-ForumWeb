package com.api.forumweb.app.domain.dto.dtousuario;

/**
 * Dados de autenticação de usuário.
 *
 * @param email O email do usuário.
 * @param senha A senha do usuário.
 */
public record DadosAutenticacao(
        String email,
        String senha
) {}
