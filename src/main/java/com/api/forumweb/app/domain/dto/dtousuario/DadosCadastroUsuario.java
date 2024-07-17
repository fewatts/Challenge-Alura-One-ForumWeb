package com.api.forumweb.app.domain.dto.dtousuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Dados para cadastro de um novo usuário.
 *
 * @param nome O nome do usuário.
 * @param email O email do usuário.
 * @param senha A senha do usuário.
 */
public record DadosCadastroUsuario(
        @NotBlank String nome,

        @Email @NotBlank String email,

        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "A senha deve ter no mínimo 6 caracteres, incluindo pelo menos uma letra maiúscula e um caractere especial.")
        String senha
) {}
