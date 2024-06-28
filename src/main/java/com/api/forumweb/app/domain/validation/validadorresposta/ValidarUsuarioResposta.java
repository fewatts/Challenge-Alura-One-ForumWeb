package com.api.forumweb.app.domain.validation.validadorresposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtoresposta.DadosCadastroRespostas;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

@Component
public class ValidarUsuarioResposta {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(DadosCadastroRespostas dados){
        var usuario = usuarioRepository.findById(dados.idUsuario());
        if(!usuario.isPresent()){
            throw new ValidacaoException("Usuario de id '" + dados.idUsuario() + "' não existe.");
        }
    }
}
