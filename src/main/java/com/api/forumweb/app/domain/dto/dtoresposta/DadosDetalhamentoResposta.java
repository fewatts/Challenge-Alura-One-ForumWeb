package com.api.forumweb.app.domain.dto.dtoresposta;

import java.time.LocalDateTime;

import com.api.forumweb.app.domain.model.Resposta;

/**
 * Dados detalhados de uma resposta para apresentação.
 *
 * @param id          O ID da resposta.
 * @param mensagem    A mensagem da resposta.
 * @param dataCriacao A data de criação da resposta.
 * @param solucao     A solução apresentada pela resposta.
 * @param idUsuario   O ID do usuário que criou a resposta.
 */
public record DadosDetalhamentoResposta(
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        String solucao,
        Long idUsuario
    ) {
    /**
     * Construtor que recebe um objeto Resposta para inicialização dos dados
     * detalhados.
     *
     * @param resposta O objeto Resposta a partir do qual os dados serão
     *                 inicializados.
     */
    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao(), resposta.getSolucao(),
                resposta.getUsuario().getId());
    }
}
