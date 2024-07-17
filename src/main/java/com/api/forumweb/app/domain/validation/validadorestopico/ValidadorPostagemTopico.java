package com.api.forumweb.app.domain.validation.validadorestopico;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;

/**
 * Interface para validação dos dados de cadastro de um tópico.
 */
public interface ValidadorPostagemTopico {

    /**
     * Valida os dados de cadastro de um tópico.
     *
     * @param dados Os dados de cadastro do tópico a serem validados.
     */
    void validar(DadosCadastroTopico dados);
}
