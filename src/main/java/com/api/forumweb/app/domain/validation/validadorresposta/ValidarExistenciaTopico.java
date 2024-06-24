package com.api.forumweb.app.domain.validation.validadorresposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtoresposta.DadosCadastroRespostas;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

@Component
public class ValidarExistenciaTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DadosCadastroRespostas dados){
        var topico = topicoRepository.findById(dados.idTopico());
        if(!topico.isPresent()){
            throw new ValidacaoException("Topico de id '" + dados.idTopico() + "' não existe");
        }
    }

}
