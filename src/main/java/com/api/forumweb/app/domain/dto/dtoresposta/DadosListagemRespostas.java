package com.api.forumweb.app.domain.dto.dtoresposta;

import java.time.LocalDateTime;

import com.api.forumweb.app.domain.model.Resposta;

public record DadosListagemRespostas(Long id, String mensagem, String tituloTopico, LocalDateTime dataCriacao, String solucao) {

    public DadosListagemRespostas(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getTopico().getTitulo(), resposta.getDataCriacao(), resposta.getSolucao());
    }

}
