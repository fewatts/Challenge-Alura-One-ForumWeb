package com.api.forumweb.app.domain.dto.dtoresposta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Dados para o cadastro de uma resposta.
 *
 * @param mensagem A mensagem da resposta.
 * @param idTopico O ID do tópico ao qual a resposta pertence.
 * @param idUsuario O ID do usuário que criou a resposta.
 * @param dataCriacao A data de criação da resposta.
 * @param solucao A solução apresentada pela resposta.
 */
public record DadosCadastroRespostas(
        @NotBlank String mensagem,
        @NotNull Long idTopico,
        @NotNull Long idUsuario,
        LocalDateTime dataCriacao,
        @NotBlank String solucao
) {
}
