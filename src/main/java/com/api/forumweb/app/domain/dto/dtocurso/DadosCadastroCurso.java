package com.api.forumweb.app.domain.dto.dtocurso;

import jakarta.validation.constraints.NotBlank;

/**
 * Dados para o cadastro de um curso.
 *
 * @param nome O nome do curso. Não pode ser nulo ou vazio.
 * @param categoria A categoria do curso. Não pode ser nula ou vazia.
 */
public record DadosCadastroCurso(
        @NotBlank String nome,
        @NotBlank String categoria
    ) {
}
