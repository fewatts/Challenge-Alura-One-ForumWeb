package com.api.forumweb.app.domain.validation.validadorcurso;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.forumweb.app.domain.model.Curso;
import com.api.forumweb.app.domain.repository.TopicoRepository;
import com.api.forumweb.app.domain.validation.ValidacaoException;

@Component
public class ValidarExistenciaDeCursoComTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    public boolean validar(Curso curso) {
        var naoExisteTopicoComEsseCurso = topicoRepository.countByCursoId(curso.getId()) == 0;
        if(!naoExisteTopicoComEsseCurso){
            throw new ValidacaoException("Cursos com tópicos associados não podem ser apagados.");
        }
        return naoExisteTopicoComEsseCurso;
    }
    
}
