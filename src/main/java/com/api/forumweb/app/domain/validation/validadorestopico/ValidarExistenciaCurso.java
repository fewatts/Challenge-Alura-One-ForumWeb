package com.api.forumweb.app.domain.validation.validadorestopico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.dto.dtotopico.DadosCadastroTopico;
import com.api.forumweb.app.domain.repository.CursoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

/**
 * Validador responsável por verificar a existência de um curso na base de dados
 * durante a postagem de um tópico.
 */
@Component
public class ValidarExistenciaCurso implements ValidadorPostagemTopico {

    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Valida a existência do curso na base de dados.
     *
     * @param dados Os dados de cadastro do tópico que incluem o ID do curso a ser
     *              validado.
     * @throws ValidacaoException Caso o curso não seja encontrado na base de dados.
     */
    @Override
    public void validar(DadosCadastroTopico dados) {
        var curso = cursoRepository.findById(dados.idCurso());
        if (!curso.isPresent()) {
            throw new ValidacaoException("Esse curso não existe na base de dados");
        }
    }
}
