package com.api.forumweb.app.domain.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.DadosCadastroTopico;
import com.api.forumweb.app.domain.repository.TopicoRepository;

@Component
public class ValidarTopicoDuplicado implements ValidarPostagemTopico{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DadosCadastroTopico dados) {
        if(topicoRepository.existsByTituloIgnoreCase(dados.titulo()) || topicoRepository.existsByMensagemIgnoreCase(dados.mensagem())){
            throw new ValidacaoException("O tópico de titulo '" + dados.titulo() + "' com mensagem '" + dados.mensagem() + "' já existe.");
        }
    }
    
    

}
