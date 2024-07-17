package com.api.forumweb.app.domain.dto.dtotopico;

import java.time.LocalDateTime;
import java.util.List;

import com.api.forumweb.app.domain.model.Curso;
import com.api.forumweb.app.domain.model.Resposta;
import com.api.forumweb.app.domain.model.Topico;

/**
 * Dados resumidos de um tópico para listagem.
 *
 * @param id O ID do tópico.
 * @param titulo O título do tópico.
 * @param mensagem A mensagem do tópico.
 * @param dataCriacao A data de criação do tópico.
 * @param status O status do tópico (ativo ou inativo).
 * @param curso O curso associado ao tópico.
 * @param respostas A lista de respostas associadas ao tópico.
 * @param usuario O nome do usuário criador do tópico.
 */
public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Boolean status,
        Curso curso,
        List<Resposta> respostas,
        String usuario
) {
    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getCurso(), topico.getRespostas(), topico.getUsuario().getNome());
    }
}
