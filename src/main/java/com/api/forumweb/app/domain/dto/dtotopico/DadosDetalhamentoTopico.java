package com.api.forumweb.app.domain.dto.dtotopico;

import java.time.LocalDateTime;

import com.api.forumweb.app.domain.model.Curso;
import com.api.forumweb.app.domain.model.Topico;

/**
 * Dados detalhados de um tópico para apresentação.
 *
 * @param id          O ID do tópico.
 * @param titulo      O título do tópico.
 * @param mensagem    A mensagem do tópico.
 * @param dataCriacao A data de criação do tópico.
 * @param status      O status do tópico (ativo ou inativo).
 * @param curso       O curso associado ao tópico.
 * @param idUsuario   O ID do usuário criador do tópico.
 */
public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Boolean status,
        Curso curso,
        Long idUsuario
    ) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(),
                topico.getCurso(), topico.getUsuario().getId());
    }
}
