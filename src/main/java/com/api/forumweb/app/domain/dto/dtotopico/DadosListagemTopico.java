package com.api.forumweb.app.domain.dto.dtotopico;

import java.time.LocalDateTime;

import com.api.forumweb.app.domain.model.Topico;

public record DadosListagemTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, Boolean status) {
    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus());
    }
}
