package com.api.forumweb.app.domain.validation.validadorcurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtocurso.DadosCadastroCurso;
import com.api.forumweb.app.domain.repository.CursoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

/**
 * Validador responsável por verificar se um curso já existe antes de cadastrar
 * um novo.
 */
@Component
public class ValidarCursoDuplicado {

    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Valida se o nome do curso já existe no repositório.
     *
     * @param dados Dados de cadastro do curso contendo o nome a ser verificado.
     * @throws ValidacaoException Se o curso com o mesmo nome já existir.
     */
    public void validar(DadosCadastroCurso dados) {
        if (cursoRepository.existsByNomeIgnoreCase(dados.nome())) {
            throw new ValidacaoException("O curso '" + dados.nome() + "' já existe.");
        }
    }
}
