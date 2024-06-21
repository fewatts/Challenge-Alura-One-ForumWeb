package com.api.forumweb.app.domain.validation.validadorestopico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

@Component
public class ValidarTopicoDuplicado implements ValidadorPostagemTopico{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DadosCadastroTopico dados) {
        if(topicoRepository.existsByTituloIgnoreCase(dados.titulo()) && topicoRepository.existsByMensagemIgnoreCase(dados.mensagem())){
            throw new ValidacaoException("O tópico de titulo '" + dados.titulo() + "' com mensagem '" + dados.mensagem() + "' já existe.");
        }
    }
    
    

}
