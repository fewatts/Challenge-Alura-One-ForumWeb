package com.api.forumweb.app.domain.dto.dtoresposta;

import java.time.LocalDateTime;

import com.api.forumweb.app.domain.model.Resposta;

public record DadosDetalhamentoResposta (Long id, String mensagem, LocalDateTime dataCriacao, String solucao, Long idUsuario){

    public DadosDetalhamentoResposta(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao(), resposta.getSolucao(), resposta.getUsuario().getId());
    }

}
