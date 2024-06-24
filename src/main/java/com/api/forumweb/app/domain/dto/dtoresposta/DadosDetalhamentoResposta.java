package com.api.forumweb.app.domain.dto.dtoresposta;

import java.time.LocalDateTime;

import com.api.forumweb.app.domain.model.Resposta;

public record DadosDetalhamentoResposta (Long id, String mensagem, LocalDateTime dataCriacao, String solucao){

    public DadosDetalhamentoResposta(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao(), resposta.getSolucao());
    }

}
