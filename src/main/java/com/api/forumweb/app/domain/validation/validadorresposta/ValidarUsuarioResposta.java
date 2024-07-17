package com.api.forumweb.app.domain.validation.validadorresposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtoresposta.DadosCadastroRespostas;
import com.api.forumweb.app.domain.repository.UsuarioRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

/**
 * Validador responsável por verificar a existência de um usuário na base de
 * dados durante o cadastro de respostas.
 */
@Component
public class ValidarUsuarioResposta {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Valida se um usuário com o ID especificado existe na base de dados.
     *
     * @param dados Os dados de cadastro da resposta contendo o ID do usuário a ser
     *              verificado.
     * @throws ValidacaoException Caso o usuário com o ID especificado não exista.
     */
    public void validar(DadosCadastroRespostas dados) {
        var usuario = usuarioRepository.findById(dados.idUsuario());
        if (!usuario.isPresent()) {
            throw new ValidacaoException("Usuário de ID '" + dados.idUsuario() + "' não existe.");
        }
    }
}
