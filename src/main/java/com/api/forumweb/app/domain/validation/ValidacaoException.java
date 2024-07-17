package com.api.forumweb.app.domain.validation;

/**
 * Exceção personalizada para lançar durante validações de negócio.
 */
public class ValidacaoException extends RuntimeException {
    
    /**
     * Construtor que recebe uma mensagem de erro para a exceção.
     *
     * @param mensagem A mensagem descritiva da exceção.
     */
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
