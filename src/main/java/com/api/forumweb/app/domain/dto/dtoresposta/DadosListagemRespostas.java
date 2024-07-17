package com.api.forumweb.app.domain.dto.dtoresposta;

import java.time.LocalDateTime;

import com.api.forumweb.app.domain.model.Resposta;

/**
 * Dados resumidos de uma resposta para listagem.
 *
 * @param id           O ID da resposta.
 * @param mensagem     A mensagem da resposta.
 * @param tituloTopico O título do tópico ao qual a resposta está relacionada.
 * @param nomeAutor    O nome do autor da resposta.
 * @param dataCriacao  A data de criação da resposta.
 * @param solucao      A solução apresentada pela resposta.
 */
public record DadosListagemRespostas(
        Long id,
        String mensagem,
        String tituloTopico,
        String nomeAutor,
        LocalDateTime dataCriacao,
        String solucao
    ) {
    /**
     * Construtor que recebe um objeto Resposta para inicialização dos dados
     * resumidos.
     *
     * @param resposta O objeto Resposta a partir do qual os dados serão
     *                 inicializados.
     */
    public DadosListagemRespostas(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getTopico().getTitulo(),
                resposta.getUsuario().getNome(), resposta.getDataCriacao(), resposta.getSolucao());
    }
}
