package com.api.forumweb.app.domain.dto.dtotopico;

import jakarta.validation.constraints.NotNull;

/**
 * Dados para cadastro de um novo tópico.
 *
 * @param titulo O título do tópico.
 * @param mensagem A mensagem do tópico.
 * @param status O status do tópico (ativo ou inativo).
 * @param idCurso O ID do curso relacionado ao tópico.
 * @param idUsuario O ID do usuário criador do tópico.
 */
public record DadosCadastroTopico(
        @NotNull String titulo,
        @NotNull String mensagem,
        @NotNull Boolean status,
        @NotNull Long idCurso,
        @NotNull Long idUsuario
) {
}
