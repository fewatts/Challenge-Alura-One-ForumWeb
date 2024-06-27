package com.api.forumweb.app.domain.dto.dtotopico;

import java.time.LocalDateTime;
import java.util.List;

import com.api.forumweb.app.domain.model.Curso;
import com.api.forumweb.app.domain.model.Resposta;
import com.api.forumweb.app.domain.model.Topico;

public record DadosListagemTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, Boolean status, Curso curso, List<Resposta> respostas, String usuario) {
    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getCurso(), topico.getRespostas(), topico.getUsuario().getNome());
    }
}
