package com.api.forumweb.app.infra.exception;

import org.springframework.validation.FieldError;

/**
 * Representa os dados de erro de validação de um campo, incluindo o nome do campo e a mensagem de erro associada.
 */
public record DadosErroValidacao(String campo, String mensagem) {

    /**
     * Construtor que cria um objeto DadosErroValidacao a partir de um objeto FieldError do Spring.
     *
     * @param erro O objeto FieldError contendo informações sobre o erro de validação.
     */
    public DadosErroValidacao(FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
