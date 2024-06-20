package com.api.forumweb.app.domain.dto.dtotopico;

import java.time.LocalDateTime;

import com.api.forumweb.app.domain.model.Topico;

public record DadosDetalhamentoTopico (String titulo, String mensagem, LocalDateTime dataCriacao, boolean status){

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.isStatus());
    }

}
