package com.api.forumweb.app.domain.validation.validadorestopico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

/**
 * Validador responsável por verificar a existência de um usuário na base de
 * dados durante a postagem de um tópico.
 */
@Component
public class ValidarUsuarioTopico implements ValidadorPostagemTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Valida se um usuário com o ID especificado existe na base de dados.
     *
     * @param dados Os dados de cadastro do tópico contendo o ID do usuário a ser
     *              verificado.
     * @throws ValidacaoException Caso o usuário com o ID especificado não exista.
     */
    @Override
    public void validar(DadosCadastroTopico dados) {
        var usuario = usuarioRepository.findById(dados.idUsuario());
        if (!usuario.isPresent()) {
            throw new ValidacaoException("Usuário de ID '" + dados.idUsuario() + "' não existe.");
        }
    }
}
