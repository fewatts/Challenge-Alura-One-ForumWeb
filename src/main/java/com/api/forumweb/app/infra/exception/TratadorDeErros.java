package com.api.forumweb.app.infra.exception;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.api.forumweb.app.domain.validation.ValidacaoException;

import jakarta.persistence.EntityNotFoundException;

/**
 * Classe que trata e centraliza o tratamento de exceções lançadas pelos controladores REST da aplicação.
 */
@RestControllerAdvice
public class TratadorDeErros {

    /**
     * Trata a exceção EntityNotFoundException retornando um ResponseEntity com status HTTP 404 Not Found.
     *
     * @return ResponseEntity com status HTTP 404 Not Found.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpStatus> tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    /**
     * Trata a exceção MethodArgumentTypeMismatchException retornando um ResponseEntity com status HTTP 400 Bad Request.
     *
     * @param ex A exceção MethodArgumentTypeMismatchException capturada.
     * @return ResponseEntity com status HTTP 400 Bad Request contendo uma mensagem de erro.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> tratarErroDeArgumento(MethodArgumentTypeMismatchException ex){
        return ResponseEntity.badRequest().body("Argumento incorreto: " + ex);
    }

    /**
     * Trata a exceção HttpMessageNotReadableException retornando um ResponseEntity com status HTTP 400 Bad Request.
     *
     * @param ex A exceção HttpMessageNotReadableException capturada.
     * @return ResponseEntity com status HTTP 400 Bad Request contendo a mensagem de erro da exceção.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> tratarErro400(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Trata a exceção ValidacaoException retornando um ResponseEntity com status HTTP 400 Bad Request.
     *
     * @param ex A exceção ValidacaoException capturada.
     * @return ResponseEntity com status HTTP 400 Bad Request contendo a mensagem de erro da exceção.
     */
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<String> tratarErroRegraDeNegocio(ValidacaoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Trata qualquer exceção não especificada retornando um ResponseEntity com status HTTP 500 Internal Server Error.
     *
     * @param ex A exceção genérica capturada.
     * @return ResponseEntity com status HTTP 500 Internal Server Error contendo a mensagem de erro da exceção.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErro500(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Trata a exceção MethodArgumentNotValidException retornando um ResponseEntity com status HTTP 400 Bad Request.
     *
     * @param ex A exceção MethodArgumentNotValidException capturada.
     * @return ResponseEntity com status HTTP 400 Bad Request contendo uma stream de erros de validação.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Stream<Object>> tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new));
    }

    /**
     * Trata a exceção NoSuchElementException retornando um ResponseEntity com status HTTP 404 Not Found.
     *
     * @param ex A exceção NoSuchElementException capturada.
     * @return ResponseEntity com status HTTP 404 Not Found.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<HttpStatus> tratarErroElementoNaoEncontrado(NoSuchElementException ex){
        return ResponseEntity.notFound().build();
    }

    /**
     * Trata a exceção BadCredentialsException retornando um ResponseEntity com status HTTP 401 Unauthorized.
     *
     * @param ex A exceção BadCredentialsException capturada.
     * @return ResponseEntity com status HTTP 401 Unauthorized contendo a mensagem de erro da exceção.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> tratarErro401(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
