package com.api.forumweb.app.domain.validation.validadorestopico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

@Component
public class ValidarUsuarioTopico implements ValidadorPostagemTopico{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DadosCadastroTopico dados) {
        var usuario = usuarioRepository.findById(dados.idUsuario());
        if(!usuario.isPresent()){
            throw new ValidacaoException("Usuario de id '" + dados.idUsuario() + "' não existe.");
        }
    }

    
}
