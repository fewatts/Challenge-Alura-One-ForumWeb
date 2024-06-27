package com.api.forumweb.app.domain.validation.validadorestopico;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

public class ValidarUsuario implements ValidadorPostagemTopico{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DadosCadastroTopico dados) {
        if(!usuarioRepository.findById(dados.idUsuario()).isPresent()){
            throw new ValidacaoException("Usuario de id '" + dados.idUsuario() + "' não existe.");
        }
    }

    
}
