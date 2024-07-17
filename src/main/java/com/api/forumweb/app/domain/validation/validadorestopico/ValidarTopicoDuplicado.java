package com.api.forumweb.app.domain.validation.validadorestopico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

/**
 * Validador responsável por verificar a duplicidade de um tópico na base de
 * dados durante a postagem.
 */
@Component
public class ValidarTopicoDuplicado implements ValidadorPostagemTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    /**
     * Valida se um tópico com o mesmo título e mensagem já existe na base de dados.
     *
     * @param dados Os dados de cadastro do tópico contendo título e mensagem a
     *              serem verificados.
     * @throws ValidacaoException Caso um tópico com o mesmo título e mensagem já
     *                            exista.
     */
    @Override
    public void validar(DadosCadastroTopico dados) {
        if (topicoRepository.existsByTituloIgnoreCase(dados.titulo())
                && topicoRepository.existsByMensagemIgnoreCase(dados.mensagem())) {
            throw new ValidacaoException(
                    "O tópico de título '" + dados.titulo() + "' com mensagem '" + dados.mensagem() + "' já existe.");
        }
    }
}
