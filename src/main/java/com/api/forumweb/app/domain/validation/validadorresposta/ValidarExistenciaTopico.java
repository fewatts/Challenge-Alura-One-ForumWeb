package com.api.forumweb.app.domain.validation.validadorresposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtoresposta.DadosCadastroRespostas;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

/**
 * Validador responsável por verificar a existência de um tópico na base de
 * dados durante o cadastro de respostas.
 */
@Component
public class ValidarExistenciaTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    /**
     * Valida se um tópico com o ID especificado existe na base de dados.
     *
     * @param dados Os dados de cadastro da resposta contendo o ID do tópico a ser
     *              verificado.
     * @throws ValidacaoException Caso o tópico com o ID especificado não exista.
     */
    public void validar(DadosCadastroRespostas dados) {
        var topico = topicoRepository.findById(dados.idTopico());
        if (!topico.isPresent()) {
            throw new ValidacaoException("Tópico de ID '" + dados.idTopico() + "' não existe.");
        }
    }
}
