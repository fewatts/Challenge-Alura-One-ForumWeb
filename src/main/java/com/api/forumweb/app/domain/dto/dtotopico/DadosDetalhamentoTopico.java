package com.api.forumweb.app.domain.dto.dtotopico;

import java.time.LocalDateTime;

import com.api.forumweb.app.domain.model.Curso;
import com.api.forumweb.app.domain.model.Topico;

public record DadosDetalhamentoTopico (Long id, String titulo, String mensagem, LocalDateTime dataCriacao, Boolean status, Curso curso, Long idusuario){

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getCurso(), topico.getUsuario().getId());
    }

}
