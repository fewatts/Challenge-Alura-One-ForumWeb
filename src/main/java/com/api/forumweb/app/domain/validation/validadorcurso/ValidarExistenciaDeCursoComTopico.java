package com.api.forumweb.app.domain.validation.validadorcurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.model.Curso;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

/**
 * Validador responsável por verificar se um curso possui tópicos associados
 * antes de ser removido.
 */
@Component
public class ValidarExistenciaDeCursoComTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    /**
     * Valida se o curso possui tópicos associados.
     *
     * @param curso O curso a ser verificado.
     * @return true se não existirem tópicos associados ao curso, false caso
     *         contrário.
     * @throws ValidacaoException Se o curso possui tópicos associados.
     */
    public boolean validar(Curso curso) {
        var naoExisteTopicoComEsseCurso = topicoRepository.countByCursoId(curso.getId()) == 0;
        if (!naoExisteTopicoComEsseCurso) {
            throw new ValidacaoException("Cursos com tópicos associados não podem ser apagados.");
        }
        return naoExisteTopicoComEsseCurso;
    }
}
